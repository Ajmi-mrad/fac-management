package dataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FetchAdmin {
    public boolean CheckifAdminExist(String mail , String password) throws SQLException {
        // Fetch data from the database
        Connection conn = new DbConnection().connectDB("mini-projet", "postgres", "ajmi123mrad");
        // Fetch data from the database
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM public.\"admin\" WHERE mail = '"+mail+"' AND password = '"+password+"';");
        boolean adminExist = rs.next();

        conn.close();
        return adminExist;
    }


}
