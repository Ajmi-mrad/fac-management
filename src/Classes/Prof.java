package Classes;


public class Prof {
    private String nomProf;
    private String prenomProf;
    private String mailProf;
    private String passwordProf;

    private String matiere;

    public Prof(String nomProf, String prenomProf, String mailProf, String passwordProf , String matiere) {
        this.nomProf = nomProf;
        this.prenomProf = prenomProf;
        this.mailProf = mailProf;
        this.passwordProf = passwordProf;
        this.matiere = matiere;
    }

    public String getNomProf() {
        return nomProf;
    }

    public void setNomProf(String nomProf) {
        this.nomProf = nomProf;
    }

    public String getPrenomProf() {
        return prenomProf;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public void setPrenomProf(String prenomProf) {
        this.prenomProf = prenomProf;
    }

    public String getMailProf() {
        return mailProf;
    }

    public void setMailProf(String mailProf) {
        this.mailProf = mailProf;
    }

    public String getPasswordProf() {
        return passwordProf;
    }

    public void setPasswordProf(String passwordProf) {
        this.passwordProf = passwordProf;
    }

    public String toString(){
        return "Nom : "+nomProf+" Prenom : "+prenomProf+" Mail : "+mailProf+" Matiere : "+matiere;
    }
}