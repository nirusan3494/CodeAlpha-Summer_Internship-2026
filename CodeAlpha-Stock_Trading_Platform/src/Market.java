import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Market {
    private final Map<String, Stock> availableStocks;
    private final Random random;

    public Market() {
        availableStocks = new HashMap<>();
        random = new Random();
        initializeMarket();
    }

    private void initializeMarket() {
        availableStocks.put("RELIANCE", new Stock("RELIANCE", "Reliance Industries", 2950.00));
        availableStocks.put("TCS", new Stock("TCS", "Tata Consultancy Services", 3850.00));
        availableStocks.put("INFY", new Stock("INFY", "Infosys Ltd.", 1450.00));
        availableStocks.put("HDFCBANK", new Stock("HDFCBANK", "HDFC Bank", 1650.00));
        availableStocks.put("ICICIBANK", new Stock("ICICIBANK", "ICICI Bank", 1200.00));
        availableStocks.put("SBIN", new Stock("SBIN", "State Bank of India", 820.00));
        availableStocks.put("WIPRO", new Stock("WIPRO", "Wipro Ltd.", 520.00));
        availableStocks.put("ADANIENT", new Stock("ADANIENT", "Adani Enterprises", 3100.00));
        availableStocks.put("TATAMOTORS", new Stock("TATAMOTORS", "Tata Motors", 980.00));
        availableStocks.put("BHARTIARTL", new Stock("BHARTIARTL", "Bharti Airtel", 1450.00));
    }


    public void fluctuatePrices() {
        for (Stock stock : availableStocks.values()) {
            double changePercentage = (random.nextDouble() - 0.5) * 0.05;
            double newPrice = stock.getCurrentPrice() * (1 + changePercentage);
            stock.updatePrice(newPrice);
        }
    }

    public void displayMarket() {
        System.out.println("\n====================================================================");
        System.out.printf("%-15s %-30s %-10s%n", "SYMBOL", "COMPANY", "PRICE");
        System.out.println("====================================================================");

        for (Stock stock : availableStocks.values()) {
            System.out.printf("%-15s %-30s $%-10.2f%n",
                    stock.getSymbol(),
                    stock.getName(),
                    stock.getCurrentPrice());
        }
        System.out.println("====================================================================");
    }

    public Stock getStock(String symbol) {
        return availableStocks.get(symbol.toUpperCase());
    }
}