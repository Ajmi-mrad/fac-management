package dataBase;

import Classes.Matiere;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StoreMatiere {
    public void StoreData(Matiere matiere ) throws SQLException {
        DbConnection db = new DbConnection();
        Connection conn =db.connectDB("mini-projet", "postgres", "ajmi123mrad");
        String sql = "INSERT INTO public.\"Matiere\" (idMtiere,nomMatiere,coeffMatiere) VALUES (?,? ,? )";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, matiere.getNomMatiere());
        ps.executeUpdate();
        conn.close();
    }
    public void updateMatiere(String nomMatiereText,double coeffMatiereText, int idMatiereText){
        try {
            DbConnection db = new DbConnection();
            Connection conn =db.connectDB("mini-projet", "postgres", "ajmi123mrad");
            String sql = "UPDATE public.\"Matiere\" SET nomMatiere = ?, coeffMatiere = ? WHERE idMatiere = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nomMatiereText);
            ps.setDouble(2, coeffMatiereText);
            ps.setInt(3, idMatiereText);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void deleteMatiere(int idMatiere){
        try {
            DbConnection db = new DbConnection();
            Connection conn =db.connectDB("mini-projet", "postgres", "ajmi123mrad");
            String sql = "DELETE FROM public.\"Matiere\" WHERE idMatiere = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idMatiere);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
