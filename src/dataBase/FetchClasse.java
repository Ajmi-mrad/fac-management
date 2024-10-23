package dataBase;

import java.sql.*;
import  java.util.*;
public class FetchClasse {

    public String[] fetchClasse() throws SQLException {
        Connection conn = new DbConnection().connectDB("mini-projet", "postgres", "ajmi123mrad"); // Assuming DbConnection

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT DISTINCT classe FROM public.etudiant;"); // Fetch distinct classes

        List<String> classes = new ArrayList<>();
        while (rs.next()) {
            classes.add(rs.getString("classe"));
        }

        conn.close();

        return classes.toArray(new String[classes.size()]); // Convert list to string array
    }
}

