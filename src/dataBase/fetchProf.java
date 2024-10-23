package dataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class fetchProf {
    public String[][] CheckifProfExist(String mail , String password) throws SQLException {
        // Fetch data from the database
        Connection conn = new DbConnection().connectDB("mini-projet", "postgres", "ajmi123mrad");
        // Fetch data from the database
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT matiere FROM public.\"prof\" WHERE mail = '"+mail+"' AND password = '"+password+"';");
        String [][] data = new String[1][1];
        int i = 0;
        while (rs.next()) {
            data[i][0] = rs.getString("matiere");
            i++;
        }
        conn.close();

        return data;
    }


}
