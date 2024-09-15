import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Manages the database connection and table creation for the application.
 */
public class DatabaseConnection {
    private static String JDBC_URL;
    private static String USER;
    private static String PASSWORD;

    // Load properties from the config.properties file and register the MySQL JDBC driver
    static {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            // Load properties from the config.properties file
            try (FileInputStream input = new FileInputStream("config.properties")) {
                Properties properties = new Properties();
                properties.load(input);
                JDBC_URL = properties.getProperty("db.url");
                USER = properties.getProperty("db.user");
                PASSWORD = properties.getProperty("db.password");
            }
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Establishes a connection to the database.
     *
     * @return a Connection object to the database
     * @throws SQLException if a database access error occurs
     */
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

    /**
     * Creates the necessary tables in the database if they do not exist, with proper foreign key connections.
     */
    public static void createTables() {
        // SQL statements to create tables
        String createMemberTable = "CREATE TABLE IF NOT EXISTS Member (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(255) NOT NULL, " +
                "email VARCHAR(255) NOT NULL UNIQUE, " +
                "phone VARCHAR(20), " +
                "age INT CHECK (age >= 0)" +
                ");";

        String createDepartmentTable = "CREATE TABLE IF NOT EXISTS Department (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(255) NOT NULL UNIQUE" +
                ");";

        String createSubscriptionTable = "CREATE TABLE IF NOT EXISTS Subscription (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "visitsPerMonth INT CHECK (visitsPerMonth IN (8, 15)), " +
                "department_id INT, " +
                "FOREIGN KEY (department_id) REFERENCES Department(id) " +
                "ON DELETE CASCADE ON UPDATE CASCADE" +
                ");";

        String createReservationTable = "CREATE TABLE IF NOT EXISTS Reservation (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "member_id INT, " +
                "department_id INT, " +
                "scheduleTime VARCHAR(255) NOT NULL, " +
                "dateTime DATETIME NOT NULL, " +
                "FOREIGN KEY (member_id) REFERENCES Member(id) " +
                "ON DELETE CASCADE ON UPDATE CASCADE, " +
                "FOREIGN KEY (department_id) REFERENCES Department(id) " +
                "ON DELETE CASCADE ON UPDATE CASCADE" +
                ");";

        // Execute the statements to create tables
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(createMemberTable);
            System.out.println("Table 'Member' created or already exists.");

            stmt.execute(createDepartmentTable);
            System.out.println("Table 'Department' created or already exists.");

            stmt.execute(createSubscriptionTable);
            System.out.println("Table 'Subscription' created or already exists.");

            stmt.execute(createReservationTable);
            System.out.println("Table 'Reservation' created or already exists.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}