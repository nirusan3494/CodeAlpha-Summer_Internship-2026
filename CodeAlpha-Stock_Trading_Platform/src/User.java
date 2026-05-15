public class User {
    private final int userId;
    private final String username;
    private double cashBalance;

    public User(int userId, String username, double cashBalance) {
        this.userId = userId;
        this.username = username;
        this.cashBalance = cashBalance;
    }

    public int getUserId() { return userId; }
    public String getUsername() { return username; }
    public double getCashBalance() { return cashBalance; }

    public void setCashBalance(double cashBalance) {
        this.cashBalance = cashBalance;
    }
}