
import java.sql.*;

public class connection_database {
    static String URL = "jdbc:mysql://localhost:3306/food2";
    static String USER = "root";
    static String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);

    }
}