import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {

    public static Connection getConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(
                    DataBaseConfig.getUrl(),
                    DataBaseConfig.getUsername(),
                    DataBaseConfig.getPassword()
            );

        } catch (Exception e) {

            System.out.println("Database Connection Failed!");
            e.printStackTrace();

            return null;
        }
    }
}