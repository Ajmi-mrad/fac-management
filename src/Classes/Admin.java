package Classes;


public class Admin {
    private String nomAdmin;
    private String prenomAdmin;
    private String mailAdmin;
    private String passwordAdmin;

    public Admin(String nomAdmin, String prenomAdmin, String mailAdmin, String passwordAdmin) {
        this.nomAdmin = nomAdmin;
        this.prenomAdmin = prenomAdmin;
        this.mailAdmin = mailAdmin;
        this.passwordAdmin = passwordAdmin;
    }

    public String getNomAdmin() {
        return nomAdmin;
    }

    public void setNomAdmin(String nomAdmin) {
        this.nomAdmin = nomAdmin;
    }

    public String getPrenomAdmin() {
        return prenomAdmin;
    }

    public void setPrenomAdmin(String prenomAdmin) {
        this.prenomAdmin = prenomAdmin;
    }

    public String getMailAdmin() {
        return mailAdmin;
    }

    public void setMailAdmin(String mailAdmin) {
        this.mailAdmin= mailAdmin;
    }

    public String getPasswordAdmin() {
        return passwordAdmin;
    }

    public void setPasswordAdmin(String passwordAdmin) {
        this.passwordAdmin = passwordAdmin;
    }
    public String toString(){
        return "Nom : "+nomAdmin+" Prenom : "+prenomAdmin+" Mail : "+mailAdmin+" Password : "+passwordAdmin;
    }
}