import java.util.List;
import java.util.Scanner;

public class HotelReservationSystem {
    private final HotelDAO dao;
    private final Scanner scanner;

    public HotelReservationSystem() {
        this.dao = new HotelDAO();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("=== Welcome to the CodeAlpha Hotel Reservation System ===");
        boolean running = true;

        while (running) {
            System.out.println("\n1. Search Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Booking Details");
            System.out.println("4. Cancel a Reservation");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> searchRooms();
                case 2 -> makeReservation();
                case 3 -> viewBooking();
                case 4 -> cancelReservation();
                case 5 -> {
                    System.out.println("Thank you for using the system. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }
    private void searchRooms() {


        System.out.println("             AVAILABLE ROOMS");
        System.out.println("==================================================");

        List<Room> rooms = dao.getAvailableRooms();

        if (rooms == null || rooms.isEmpty()) {

            System.out.println("\nNo rooms currently available.");
            System.out.println(" Try again later or check different dates.\n");

        } else {

            System.out.printf("%-10s %-15s %-10s%n",
                    "ROOM NO", "CATEGORY", "PRICE/NIGHT");

            System.out.println("--------------------------------------------------");

            for (Room r : rooms) {

                System.out.printf("%-10d %-15s $%-10.2f%n",
                        r.getRoomNumber(),
                        r.getCategory(),
                        r.getPrice());
            }
        }

        System.out.println("==================================================\n");
    }

    private void makeReservation() {
        System.out.print("\nEnter your name: ");
        String name = scanner.nextLine();

        searchRooms();
        System.out.print("Enter the Room Number you want to book: ");
        int roomNum;
        try {
            roomNum = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid room number.");
            return;
        }

        Room selectedRoom = dao.getRoom(roomNum);
        if (selectedRoom == null) {
            System.out.println("Error: Room is unavailable or does not exist.");
            return;
        }

        System.out.println("\n--- Payment Simulation ---");
        System.out.println("Total Due: $" + selectedRoom.getPrice());
        System.out.print("Enter payment amount to confirm: $");
        double payment = Double.parseDouble(scanner.nextLine());

        if (payment >= selectedRoom.getPrice()) {
            int resId = dao.createReservation(name, roomNum);
            if (resId != -1) {
                System.out.println("Payment successful! Reservation confirmed.");
                System.out.println("Your Reservation ID is: " + resId);
                if (payment > selectedRoom.getPrice()) {
                    System.out.printf("Change returned: $%.2f%n", (payment - selectedRoom.getPrice()));
                }
            } else {
                System.out.println("Booking failed due to a database error.");
            }
        } else {
            System.out.println("Insufficient funds. Booking failed.");
        }
    }

    private void viewBooking() {
        System.out.print("\nEnter your Reservation ID: ");
        int resId = Integer.parseInt(scanner.nextLine());

        Reservation res = dao.getReservation(resId);
        if (res != null) {
            System.out.println("\n--- Booking Details ---");
            System.out.printf("Res ID: %d | Guest: %s | Room: %d [%s] - $%.2f/night%n",
                    res.getReservationId(), res.getGuestName(),
                    res.getRoom().getRoomNumber(), res.getRoom().getCategory(), res.getRoom().getPrice());
        } else {
            System.out.println("Reservation not found.");
        }
    }

    private void cancelReservation() {
        System.out.print("\nEnter Reservation ID to cancel: ");
        int resId = Integer.parseInt(scanner.nextLine());

        if (dao.cancelReservation(resId)) {
            System.out.println("Reservation " + resId + " has been successfully canceled.");
        } else {
            System.out.println("Reservation not found or could not be canceled.");
        }
    }

    public static void main(String[] args) {
        HotelReservationSystem system = new HotelReservationSystem();
        system.start();
    }
}