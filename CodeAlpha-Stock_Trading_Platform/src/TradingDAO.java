import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class TradingDAO {


    public int loginOrCreateUser(String username) {
        String checkUser = "SELECT user_id FROM users WHERE username = ?";
        String createUser = "INSERT INTO users(username, cash_balance) VALUES(?, 10000.00)";

        try (Connection con = DataBaseConnection.getConnection()) {
            // Check existing user
            try (PreparedStatement ps = con.prepareStatement(checkUser)) {
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt("user_id");
                }
            }


            try (PreparedStatement ps = con.prepareStatement(createUser, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, username);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    System.out.println("New account created for " + username);
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }


    public double getCashBalance(int userId) {
        String query = "SELECT cash_balance FROM users WHERE user_id = ?";

        try (Connection con = DataBaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("cash_balance");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }


    public void executeTrade(int userId, String type, String symbol, int qty, double price) {
        double totalAmount = qty * price;
        String updateBalance;

        if (type.equals("BUY")) {
            double balance = getCashBalance(userId);
            if (balance < totalAmount) {
                System.out.println("Insufficient balance!");
                return;
            }
            updateBalance = "UPDATE users SET cash_balance = cash_balance - ? WHERE user_id = ?";
        } else {
            updateBalance = "UPDATE users SET cash_balance = cash_balance + ? WHERE user_id = ?";
        }

        String insertTransaction = "INSERT INTO transactions(user_id, transaction_type, symbol, quantity, price) VALUES(?,?,?,?,?)";

        try (Connection con = DataBaseConnection.getConnection()) {
            con.setAutoCommit(false);

            try (PreparedStatement balanceStmt = con.prepareStatement(updateBalance);
                 PreparedStatement transStmt = con.prepareStatement(insertTransaction)) {

                // Update balance
                balanceStmt.setDouble(1, totalAmount);
                balanceStmt.setInt(2, userId);
                balanceStmt.executeUpdate();

                // Insert transaction
                transStmt.setInt(1, userId);
                transStmt.setString(2, type);
                transStmt.setString(3, symbol);
                transStmt.setInt(4, qty);
                transStmt.setDouble(5, price);
                transStmt.executeUpdate();

                // Update holdings
                updateHoldings(con, userId, type, symbol, qty);

                // Commit all together
                con.commit();
                System.out.println("Trade Successful!");

            } catch (SQLException e) {
                con.rollback();
                System.out.println("Trade Failed!");
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Update holdings table
    private void updateHoldings(Connection con, int userId, String type, String symbol, int qty) throws SQLException {
        String checkHolding = "SELECT quantity FROM holdings WHERE user_id = ? AND symbol = ?";

        try (PreparedStatement ps = con.prepareStatement(checkHolding)) {
            ps.setInt(1, userId);
            ps.setString(2, symbol);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int currentQty = rs.getInt("quantity");

                if (type.equals("SELL") && currentQty < qty) {
                    throw new SQLException("Not enough shares!");
                }

                int newQty;
                if (type.equals("BUY")) {
                    newQty = currentQty + qty;
                } else {
                    newQty = currentQty - qty;
                }

                if (newQty == 0) {
                    String deleteQuery = "DELETE FROM holdings WHERE user_id = ? AND symbol = ?";
                    try (PreparedStatement delStmt = con.prepareStatement(deleteQuery)) {
                        delStmt.setInt(1, userId);
                        delStmt.setString(2, symbol);
                        delStmt.executeUpdate();
                    }
                } else {
                    String updateQuery = "UPDATE holdings SET quantity = ? WHERE user_id = ? AND symbol = ?";
                    try (PreparedStatement upStmt = con.prepareStatement(updateQuery)) {
                        upStmt.setInt(1, newQty);
                        upStmt.setInt(2, userId);
                        upStmt.setString(3, symbol);
                        upStmt.executeUpdate();
                    }
                }
            } else if (type.equals("BUY")) {
                String insertQuery = "INSERT INTO holdings(user_id, symbol, quantity) VALUES(?,?,?)";
                try (PreparedStatement insStmt = con.prepareStatement(insertQuery)) {
                    insStmt.setInt(1, userId);
                    insStmt.setString(2, symbol);
                    insStmt.setInt(3, qty);
                    insStmt.executeUpdate();
                }
            }
        }
    }

    // Fetch user's current stock holdings
    public Map<String, Integer> getHoldings(int userId) {
        Map<String, Integer> holdings = new HashMap<>();
        String query = "SELECT symbol, quantity FROM holdings WHERE user_id = ?";

        try (Connection con = DataBaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                holdings.put(rs.getString("symbol"), rs.getInt("quantity"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return holdings;
    }

    // Fetch and display transaction history
    public void displayTransactionHistory(int userId) {
        String query = "SELECT * FROM transactions WHERE user_id = ? ORDER BY transaction_time DESC";

        try (Connection con = DataBaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            boolean hasTransactions = false;

            while (rs.next()) {
                hasTransactions = true;
                System.out.printf("[%s] %-4s | %-5s | Qty: %-4d | Price: $%.2f | Total: $%.2f%n",
                        rs.getTimestamp("transaction_time"),
                        rs.getString("transaction_type"),
                        rs.getString("symbol"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getInt("quantity") * rs.getDouble("price")
                );
            }

            if (!hasTransactions) {
                System.out.println("No transactions found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}