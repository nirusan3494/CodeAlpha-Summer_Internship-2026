import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseCon {

    public static Connection getConnection() {

        Connection con = null;

        try {
            Class.forName(DataBaseConfig.getDriver());

            con = DriverManager.getConnection(
                    DataBaseConfig.getUrl(),
                    DataBaseConfig.getUsername(),
                    DataBaseConfig.getPassword()
            );

        } catch (Exception e) {
            System.out.println("DB Connection Failed");
            e.printStackTrace();
        }

        return con;
    }
}