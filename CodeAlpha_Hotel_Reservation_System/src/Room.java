public class Room {
    private final int roomNumber;
    private final String category;
    private final double price;
    private final boolean isAvailable;

    public Room(int roomNumber, String category, double price, boolean isAvailable) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public int getRoomNumber() { return roomNumber; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public boolean isAvailable() { return isAvailable; }
}