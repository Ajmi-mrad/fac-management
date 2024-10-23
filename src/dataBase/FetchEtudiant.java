    package dataBase;

    import java.sql.*;

    public class FetchEtudiant {
        public String[][] FetchAllData() throws SQLException {
            // Fetch data from the database
            Connection conn = new DbConnection().connectDB("mini-projet", "postgres", "ajmi123mrad");
            // Fetch data from the database
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM public.\"Etudiant\";");
            String [][] data = new String[57][13];
            int i = 0;
            while (rs.next()) {
                data[i][0] = rs.getString("id");
                data[i][1] = rs.getString("nom");
                data[i][2] = rs.getString("prenom");
                data[i][3] = rs.getString("dateNaissance");
                data[i][4] = rs.getString("math");
                data[i][5] = rs.getString("fr");
                data[i][6] = rs.getString("eng");
                data[i][7] = rs.getString("physique");
                data[i][8] = rs.getString("java");
                data[i][9] = rs.getString("systeme");
                data[i][10] = rs.getString("archi");
                data[i][11] = rs.getString("reseau");
                data[i][12] = rs.getString("classe");
                i++;
            }
            conn.close();
            return  data;
        }
        public String[][] FetchData(String matiere) throws SQLException {
            // Fetch data from the database
            Connection conn = new DbConnection().connectDB("mini-projet", "postgres", "ajmi123mrad");
            // Fetch data from the database
            Statement st = conn.createStatement();
            String sql = "SELECT \"Etudiant\".id, \"Etudiant\".nom, \"Etudiant\".prenom, \"Etudiant\"."+matiere+", \"Etudiant\".classe"+" FROM public.\"Etudiant\";";
            ResultSet rs = st.executeQuery(sql);
            String [][] data = new String[57][5];
            int i = 0;
            while (rs.next()) {
                data[i][0] = rs.getString("id");
                data[i][1] = rs.getString("nom");
                data[i][2] = rs.getString("prenom");
                data[i][3] = rs.getString(matiere);
                data[i][4] = rs.getString("classe");
                i++;
            }
            conn.close();
            return  data;

        }
        public  String[][] CheckifEtudiantExist(int idEtudiant) throws SQLException {
            // Fetch data from the database
            Connection conn = new DbConnection().connectDB("mini-projet", "postgres", "ajmi123mrad");
            // Fetch data from the database
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT id FROM public.\"Etudiant\" WHERE id = '"+idEtudiant+"';");
            String [][] data = new String[1][1];
            int i = 0;
            while (rs.next()) {
                data[i][0] = rs.getString("id");
                i++;
            }
            conn.close();
            return data;
        }
        public int getNoteEtudiant(int idEtudiant, String matiereName) throws SQLException {
            DbConnection db = new DbConnection();
            Connection conn =db.connectDB("mini-projet", "postgres", "ajmi123mrad");
            String sql = "SELECT "+matiereName+" FROM public.\"Etudiant\" WHERE id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idEtudiant);
            ResultSet rs = ps.executeQuery();
            int note = 0;
            while (rs.next()) {
                note = rs.getInt(matiereName);
            }
            conn.close();
            return note;
        }
        public String[][] fetchNomPrenomDateNaissanceClasseEtudiant(int idEtudiant) throws SQLException {
            Connection conn = new DbConnection().connectDB("mini-projet", "postgres", "ajmi123mrad");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT nom, prenom, \"dateNaissance\", classe FROM public.\"Etudiant\" WHERE id = '"+idEtudiant+"';");
            String [][] data = new String[1][4];
            int i = 0;
            while (rs.next()) {
                data[i][0] = rs.getString("nom");
                data[i][1] = rs.getString("prenom");
                data[i][2] = rs.getString("dateNaissance");
                data[i][3] = rs.getString("classe");
                i++;
            }
            conn.close();
            return data;
        }
    }
