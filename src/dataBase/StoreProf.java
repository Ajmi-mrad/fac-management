package dataBase;

import Classes.Prof;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StoreProf {
    public void StoreData(Prof p ) throws SQLException {
        // Store data in the database
        Connection conn = new DbConnection().connectDB("mini-projet", "postgres", "ajmi123mrad");
        String sql = "INSERT INTO public.\"prof\"(mail,nom, prenom, password, matiere) VALUES (?,?,?,?,?);";
        // Store data in the database
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, p.getMailProf());
        ps.setString(2, p.getNomProf());
        ps.setString(3, p.getPrenomProf());
        ps.setString(4, p.getPasswordProf());
        ps.setString(5, p.getMatiere());
        ps.executeUpdate();
        conn.close();
    }

}
