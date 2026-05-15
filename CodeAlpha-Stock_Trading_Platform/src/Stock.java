public class Stock {
    private final String symbol;
    private final String name;
    private double currentPrice;

    public Stock(String symbol, String name, double initialPrice) {
        this.symbol = symbol;
        this.name = name;
        this.currentPrice = initialPrice;
    }

    public void updatePrice(double newPrice) {
        this.currentPrice = Math.round(newPrice * 100.0) / 100.0;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }
}