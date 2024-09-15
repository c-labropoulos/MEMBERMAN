import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object (DAO) for managing Member entities in the database.
 */
public class MemberDAO {

    /**
     * Adds a member to the database.
     *
     * @param member the Member object to be added
     * @return the auto-generated ID of the newly added member, or -1 if the insertion failed
     */
    public int addMember(Member member) {
        String sql = "INSERT INTO Member (name, email, phone, age) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, member.getName());
            pstmt.setString(2, member.getEmail());
            pstmt.setString(3, member.getPhone());
            pstmt.setInt(4, member.getAge());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                // Retrieve the auto-generated ID
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int newId = generatedKeys.getInt(1);
                        member.setId(newId); // Set the ID in the Member object
                        return newId;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Return -1 if the insertion failed
    }

    /**
     * Checks if a member's email already exists in the database.
     *
     * @param email the email to check
     * @return true if the email exists, false otherwise
     */
    public boolean emailExists(String email) {
        String sql = "SELECT id FROM Member WHERE email = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Returns true if email exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}