package Classes;

import  dataBase.StoreEtudiant;
import java.sql.SQLException;

public class Etudiant {
    private final int idEtud;
    private String nom ;
    private String prenom ;
    private String dateNaissance ;
    //private ArrayList<Integer> notes ;
    private  String mail;
    private String password;
    private String classe;
    public Etudiant(int idEtud,String nom, String prenom, String dateNaissance,String classe) throws SQLException {
        this.idEtud = idEtud;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.classe=classe;
        new StoreEtudiant().StoreData(this);
    }

    public int getIdEtud() {
        return idEtud;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String  dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance='" + dateNaissance + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
