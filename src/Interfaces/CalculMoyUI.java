// Packages to import
package Interfaces;

import dataBase.FetchEtudiant;

import javax.swing.*;
import java.sql.SQLException;

public class CalculMoyUI {
    JFrame f;
    JTable j;

    JButton updateButton;

    public CalculMoyUI() throws SQLException {
        f = new JFrame();

        f.setTitle("Calcul Moyenne d'un Etudiant");

        createComponents(f);
    }
    private void createComponents(JFrame f) throws SQLException {
        String[][] data = new FetchEtudiant().FetchAllData();
        String[][] dataWithMoy = new String[data.length][6];
        float [] coef = {2,1,1,2,2,2,2,2};
        int index=0;
        for (int i = 0; i < data.length; i++) {
            double moy = 0;
            for (int j = 4; j < data[i].length-1; j++) {
                if(data[i][j] == null) {
                    break;
                }
                moy += Double.parseDouble(data[i][j]) * coef[j-4];
            }
            if (data[i][0]!=null){
                moy = moy / 14;
                dataWithMoy[i][0] = data[i][0];
                dataWithMoy[i][1] = data[i][1];
                dataWithMoy[i][2] = data[i][2];
                dataWithMoy[i][3] = data[i][3];
                dataWithMoy[i][4] = String.valueOf(moy);
                dataWithMoy[i][5]=data[i][12];
                }

        }
        // sort table data with moy
        for (int i = 0; i < dataWithMoy.length; i++) {
            for (int j = i+1; j < dataWithMoy.length; j++) {
                if (dataWithMoy[i][4] != null && dataWithMoy[j][4] != null) {
                    if (Double.parseDouble(dataWithMoy[i][4]) < Double.parseDouble(dataWithMoy[j][4])) {
                        String[] temp = dataWithMoy[i];
                        dataWithMoy[i] = dataWithMoy[j];
                        dataWithMoy[j] = temp;
                    }
                }
            }
        }

        String[] columnNames = { "IdEtduaint","nomEtudiant", "prenonEtudiant","dateNaissance","Moyenne","classe" };//"Matiere
        final JTable jt = new JTable(dataWithMoy, columnNames);
        jt.setBounds(30, 40, 200, 300);


        JScrollPane sp = new JScrollPane(jt);
        f.add(sp);
        f.setSize(500, 200);
        f.setVisible(true);
    }


}
