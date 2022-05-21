package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection dbConnection;
    private Connection connection;

    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/SuperMarket", "root", "1234");
    }
    public static DBConnection getDbConnection() throws SQLException, ClassNotFoundException {

        return (dbConnection==null)?dbConnection= new DBConnection():dbConnection;

    }

    public Connection getConnection(){
        return connection;
    }
}
