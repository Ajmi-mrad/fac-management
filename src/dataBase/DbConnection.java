package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection  {
    public Connection connectDB(String dbName , String user , String password){
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbName, user, password);
            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
