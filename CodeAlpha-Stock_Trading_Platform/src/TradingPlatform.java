import java.util.Map;
import java.util.Scanner;

public class TradingPlatform {

    private final Market market;
    private final TradingDAO dao;
    private final Scanner sc;
    private User currentUser;

    public TradingPlatform() {
        market = new Market();
        dao = new TradingDAO();
        sc = new Scanner(System.in);
        login();
    }

    private void login() {
        System.out.println("=========================================");
        System.out.println("  WELCOME TO THE STOCK TRADING PLATFORM  ");
        System.out.println("=========================================");
        System.out.print("Enter your username to Login or Create Account: ");
        String username = sc.nextLine();

        // Fetch ID from database or create new user
        int userId = dao.loginOrCreateUser(username);

        if (userId != -1) {
            double balance = dao.getCashBalance(userId);
            currentUser = new User(userId, username, balance);
            System.out.println("\nWelcome, " + currentUser.getUsername() + "!");
        } else {
            System.out.println("Database connection failed. Exiting.");
            System.exit(0);
        }
    }

    public void start() {
        int choice;
        do {
            market.fluctuatePrices(); // Prices change every time the menu loads
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio Performance");
            System.out.println("5. View Transaction History");
            System.out.println("6. Logout & Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1 -> market.displayMarket();
                case 2 -> buyStock();
                case 3 -> sellStock();
                case 4 -> displayPortfolio();
                case 5 -> displayHistory();
                case 6 -> System.out.println("Logging out...!");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 6);
    }

    private void buyStock() {
        System.out.print("Enter Stock Symbol to Buy: ");
        String symbol = sc.nextLine().toUpperCase();
        Stock stock = market.getStock(symbol);

        if (stock == null) {
            System.out.println("Invalid Stock Symbol.");
            return;
        }

        System.out.printf("Current Price of %s: $%.2f%n", symbol, stock.getCurrentPrice());
        System.out.print("Enter quantity to buy: ");
        int qty = sc.nextInt();

        // Pass the user ID to the Database DAO
        dao.executeTrade(currentUser.getUserId(), "BUY", symbol, qty, stock.getCurrentPrice());
    }

    private void sellStock() {
        System.out.print("Enter Stock Symbol to Sell: ");
        String symbol = sc.nextLine().toUpperCase();
        Stock stock = market.getStock(symbol);

        if (stock == null) {
            System.out.println("Invalid Stock Symbol.");
            return;
        }

        System.out.print("Enter quantity to sell: ");
        int qty = sc.nextInt();

        // Pass the user ID to the Database DAO
        dao.executeTrade(currentUser.getUserId(), "SELL", symbol, qty, stock.getCurrentPrice());
    }

    private void displayPortfolio() {
        System.out.println("\n===== YOUR PORTFOLIO =====");

        // Fetch Live Balance from DB
        double cashBalance = dao.getCashBalance(currentUser.getUserId());
        currentUser.setCashBalance(cashBalance); // Update local object
        System.out.printf("Cash Balance: $%.2f%n", currentUser.getCashBalance());

        double totalInvestmentValue = 0.0;
        System.out.println("\nHoldings:");

        // Fetch Live Holdings from DB
        Map<String, Integer> holdings = dao.getHoldings(currentUser.getUserId());

        if (holdings.isEmpty()) {
            System.out.println("You currently own no stocks.");
        } else {
            for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
                String symbol = entry.getKey();
                int qty = entry.getValue();

                Stock currentMarketStock = market.getStock(symbol);
                double currentPrice = currentMarketStock != null ? currentMarketStock.getCurrentPrice() : 0.0;
                double totalValue = qty * currentPrice;
                totalInvestmentValue += totalValue;

                System.out.printf("%-10s | Shares: %-4d | Current Price: $%-7.2f | Total Value: $%.2f%n",
                        symbol, qty, currentPrice, totalValue);
            }
        }

        System.out.printf("\nTotal Portfolio Value (Cash + Investments): $%.2f%n",
                (cashBalance + totalInvestmentValue));
    }

    private void displayHistory() {
        System.out.println("\n===== TRANSACTION HISTORY =====");
        dao.displayTransactionHistory(currentUser.getUserId());
    }

    public static void main(String[] args) {
        TradingPlatform platform = new TradingPlatform();
        platform.start();
    }
}