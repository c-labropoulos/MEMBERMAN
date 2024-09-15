import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Data Access Object (DAO) for managing Reservation entities in the database.
 */
public class ReservationDAO {

    /**
     * Adds a reservation to the database.
     *
     * @param reservation the Reservation object to be added
     * @return the auto-generated ID of the newly added reservation, or -1 if the insertion failed
     */
    public int addReservation(Reservation reservation) {
        String sql = "INSERT INTO Reservation (member_id, department_id, scheduleTime, dateTime) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, reservation.getMember().getId());
            pstmt.setInt(2, reservation.getDepartment().getId());
            pstmt.setString(3, reservation.getScheduleTime());
            pstmt.setTimestamp(4, Timestamp.valueOf(reservation.getDateTime()));

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                // Retrieve the auto-generated ID
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int newId = generatedKeys.getInt(1);
                        reservation.setId(newId); // Set the ID in the Reservation object
                        return newId;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Return -1 if the insertion failed
    }
}