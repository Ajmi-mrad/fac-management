package dataBase;

import Classes.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StoreAdmin {
    public void StoreData(Admin a ) throws SQLException {
        // Store data in the database
        Connection conn = new DbConnection().connectDB("mini-projet", "postgres", "ajmi123mrad");
        String sql = "INSERT INTO public.\"admin\"(mail,nom, prenom, password) VALUES (?,?,?,?);";
        // Store data in the database
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, a.getMailAdmin());
        ps.setString(2, a.getNomAdmin());
        ps.setString(3, a.getPrenomAdmin());
        ps.setString(4, a.getPasswordAdmin());
        ps.executeUpdate();
        conn.close();
    }

}
