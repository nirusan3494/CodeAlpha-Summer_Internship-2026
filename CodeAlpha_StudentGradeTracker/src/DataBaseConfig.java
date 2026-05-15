import java.io.FileInputStream;
import java.util.Properties;

public class DataBaseConfig {

    private static Properties props = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream("db.properties");
            props.load(fis);
        } catch (Exception e) {
            System.out.println("Cannot load db.properties");
            e.printStackTrace();
        }
    }

    public static String getUrl() {
        return props.getProperty("db.url");
    }

    public static String getUsername() {
        return props.getProperty("db.username");
    }

    public static String getPassword() {
        return props.getProperty("db.password");
    }

    public static String getDriver() {
        return props.getProperty("db.driver");
    }
}