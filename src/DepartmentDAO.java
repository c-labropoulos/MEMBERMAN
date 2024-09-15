import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object (DAO) for managing Department entities in the database.
 */
public class DepartmentDAO {

    /**
     * Adds a department to the database.
     *
     * @param department the Department object to be added
     * @return the auto-generated ID of the newly added department, or -1 if the insertion failed
     */
    public int addDepartment(Department department) {
        String sql = "INSERT INTO Department (name) VALUES (?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, department.getName());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                // Retrieve the auto-generated ID
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int newId = generatedKeys.getInt(1);
                        department.setId(newId); // Set the ID in the Department object
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
     * Checks if a department exists by name.
     *
     * @param name the name of the department to check
     * @return true if the department exists, false otherwise
     */
    public boolean checkDepartmentExists(String name) {
        String sql = "SELECT id FROM Department WHERE name = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Returns true if department exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}