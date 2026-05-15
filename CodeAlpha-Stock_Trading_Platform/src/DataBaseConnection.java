import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {

    // Method to establish database connection
    public static Connection getConnection() {

        Connection con = null;

        try {

            // Load JDBC Driver
            Class.forName(DataBaseConfig.getDriver());

            // Create Connection
            con = DriverManager.getConnection(
                    DataBaseConfig.getUrl(),
                    DataBaseConfig.getUsername(),
                    DataBaseConfig.getPassword()
            );

            System.out.println("Database Connected Successfully!");

        } catch (Exception e) {

            System.out.println("Database Connection Failed!");
            e.printStackTrace();
        }

        return con;
    }
}