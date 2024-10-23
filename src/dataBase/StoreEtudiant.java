package dataBase;

import Classes.Etudiant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StoreEtudiant {
    public void StoreData(Etudiant etudiant ) throws SQLException {
        DbConnection db = new DbConnection();
        Connection conn =db.connectDB("mini-projet", "postgres", "ajmi123mrad");
        String sql = "INSERT INTO public.\"Etudiant\" (id, nom, prenom, math, fr, eng, physique, java, systeme, archi, reseau,\"dateNaissance\",classe) VALUES (?,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, etudiant.getIdEtud());
        ps.setString(2, etudiant.getNom());
        ps.setString(3, etudiant.getPrenom());
        ps.setString(12, etudiant.getDateNaissance());
        ps.setString(13, etudiant.getClasse());
        for (int i = 0; i < 8; i++) {
            ps.setInt(i + 4,0);
        }
        ps.executeUpdate();
        conn.close();
    }
    public  void AjouterNotes(int idEtudiant , String matiereName,int matiereNote) throws SQLException {
        DbConnection db = new DbConnection();
        Connection conn =db.connectDB("mini-projet", "postgres", "ajmi123mrad");
        String sql = "UPDATE public.\"Etudiant\" SET "+matiereName+" = ? WHERE id = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, matiereNote);
        ps.setInt(2, idEtudiant);
        ps.executeUpdate();
        conn.close();
    }
    public void updateEtudiant(int idEtudiantText,String nomEtudiantText,String prenomEtudiantText,String dateNaissanceEtudiantText,String classeEtudiantText){
        try {
            DbConnection db = new DbConnection();
            Connection conn =db.connectDB("mini-projet", "postgres", "ajmi123mrad");
            String sql = "UPDATE public.\"Etudiant\" SET nom = ?, prenom = ?, \"dateNaissance\" = ? , classe= ? WHERE id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nomEtudiantText);
            ps.setString(2, prenomEtudiantText);
            ps.setString(3, dateNaissanceEtudiantText);
            ps.setInt(5, idEtudiantText);
            ps.setString(4, classeEtudiantText);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void deleteEtudiant(int idEtudiant){
        try {
            DbConnection db = new DbConnection();
            Connection conn =db.connectDB("mini-projet", "postgres", "ajmi123mrad");
            String sql = "DELETE FROM public.\"Etudiant\" WHERE id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idEtudiant);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
