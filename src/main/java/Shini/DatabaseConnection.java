package Shini;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/Shini_SuperMarket";
    private static final String user = "root";
//    private static final String password = "root1234";

    // Hanade Password
    private static final String password = "Ha122@sQL";

    // Private constructor to prevent instantiation
    private DatabaseConnection() {}

    public static Connection getConnection() throws SQLException {
        try{
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        return DriverManager.getConnection(url, user, password);
    }
}
