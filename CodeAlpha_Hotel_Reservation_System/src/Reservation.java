public class Reservation {
    private final int reservationId;
    private final String guestName;
    private final Room room;

    public Reservation(int reservationId, String guestName, Room room) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.room = room;
    }

    public int getReservationId() { return reservationId; }
    public String getGuestName() { return guestName; }
    public Room getRoom() { return room; }
}