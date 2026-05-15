import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {

    public List<Room> getAvailableRooms() {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM rooms WHERE is_available = TRUE";

        try (Connection con = DataBaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                rooms.add(new Room(
                        rs.getInt("room_number"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getBoolean("is_available")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public Room getRoom(int roomNumber) {
        String query = "SELECT * FROM rooms WHERE room_number = ? AND is_available = TRUE";
        try (Connection con = DataBaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, roomNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Room(
                        rs.getInt("room_number"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getBoolean("is_available")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int createReservation(String guestName, int roomNumber) {
        String updateRoom = "UPDATE rooms SET is_available = FALSE WHERE room_number = ?";
        String insertRes = "INSERT INTO reservations (guest_name, room_number) VALUES (?, ?)";

        try (Connection con = DataBaseConnection.getConnection()) {
            con.setAutoCommit(false); // Start transaction

            try (PreparedStatement updateStmt = con.prepareStatement(updateRoom);
                 PreparedStatement insertStmt = con.prepareStatement(insertRes, Statement.RETURN_GENERATED_KEYS)) {

                // 1. Mark room as unavailable
                updateStmt.setInt(1, roomNumber);
                updateStmt.executeUpdate();

                // 2. Create reservation
                insertStmt.setString(1, guestName);
                insertStmt.setInt(2, roomNumber);
                insertStmt.executeUpdate();

                ResultSet rs = insertStmt.getGeneratedKeys();
                int resId = -1;
                if (rs.next()) {
                    resId = rs.getInt(1);
                }

                con.commit(); // Confirm transaction
                return resId;

            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public Reservation getReservation(int reservationId) {
        String query = "SELECT r.reservation_id, r.guest_name, rm.room_number, rm.category, rm.price " +
                "FROM reservations r JOIN rooms rm ON r.room_number = rm.room_number " +
                "WHERE r.reservation_id = ?";

        try (Connection con = DataBaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, reservationId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Room room = new Room(
                        rs.getInt("room_number"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        false
                );
                return new Reservation(rs.getInt("reservation_id"), rs.getString("guest_name"), room);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean cancelReservation(int reservationId) {
        String findRoomQuery = "SELECT room_number FROM reservations WHERE reservation_id = ?";

        try (Connection con = DataBaseConnection.getConnection()) {
            // Find room number first
            int roomNumber = -1;
            try (PreparedStatement ps = con.prepareStatement(findRoomQuery)) {
                ps.setInt(1, reservationId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) roomNumber = rs.getInt("room_number");
            }

            if (roomNumber == -1) return false;

            // Transaction to delete reservation and free room
            con.setAutoCommit(false);
            try (PreparedStatement delRes = con.prepareStatement("DELETE FROM reservations WHERE reservation_id = ?");
                 PreparedStatement updateRoom = con.prepareStatement("UPDATE rooms SET is_available = TRUE WHERE room_number = ?")) {

                delRes.setInt(1, reservationId);
                delRes.executeUpdate();

                updateRoom.setInt(1, roomNumber);
                updateRoom.executeUpdate();

                con.commit();
                return true;
            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}