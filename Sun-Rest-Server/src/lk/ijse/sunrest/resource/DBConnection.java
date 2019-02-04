package lk.ijse.sunrest.resource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private final Connection connection;
    private static DBConnection dbConnection;

    private DBConnection() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost/sunrest", "root", "2986");

    }

    public Connection getConnection(){
        return connection;
    }

    public static DBConnection getIntance() throws ClassNotFoundException, SQLException, IOException {
        if(dbConnection==null){
            dbConnection=new DBConnection();
        }
        return dbConnection;
    }

}
