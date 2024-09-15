import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object (DAO) for managing Subscription entities in the database.
 */
public class SubscriptionDAO {

    /**
     * Adds a subscription to the database.
     *
     * @param subscription the Subscription object to be added
     * @return the auto-generated ID of the newly added subscription, or -1 if the insertion failed
     */
    public int addSubscription(Subscription subscription) {
        String sql = "INSERT INTO Subscription (visitsPerMonth, department_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, subscription.getVisitsPerMonth());
            pstmt.setInt(2, subscription.getDepartment().getId());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                // Retrieve the auto-generated ID
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int newId = generatedKeys.getInt(1);
                        subscription.setId(newId); // Set the ID in the Subscription object
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