// Packages to import
package Interfaces;

import dataBase.FetchEtudiant;

import javax.swing.*;
import java.sql.SQLException;

public class DisplayEtudiantUI {
    JFrame f;
    JTable j;

    JButton updateButton;

    public DisplayEtudiantUI(String matiereName) throws SQLException {
        f = new JFrame();

        f.setTitle("Etudiant Informations");

        createComponents(f,matiereName);
    }
    private void createComponents(JFrame f,String matiereName  ) throws SQLException {
        String[][] data = new FetchEtudiant().FetchData(matiereName);

        String[] columnNames = { "IdEtduaint","nomEtudiant", "prenonEtudiant","Matiere","Classe" };
        final JTable jt = new JTable(data, columnNames);
        jt.setBounds(30, 40, 200, 300);


        JScrollPane sp = new JScrollPane(jt);
        f.add(sp);
        f.setSize(500, 200);
        f.setVisible(true);
    }


}