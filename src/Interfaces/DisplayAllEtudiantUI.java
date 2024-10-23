// Packages to import
package Interfaces;

import dataBase.FetchEtudiant;

import javax.swing.*;
import java.sql.SQLException;

public class DisplayAllEtudiantUI {
    JFrame f;
    JTable j;

    JButton updateButton;

    public DisplayAllEtudiantUI() throws SQLException {
        f = new JFrame();

        f.setTitle("Etudiant Informations");

        createComponents(f);
    }
    private void createComponents(JFrame f) throws SQLException {
        String[][] data = new FetchEtudiant().FetchAllData();

        String[] columnNames = { "IdEtduaint","nomEtudiant", "prenonEtudiant","dateNaissance","math","fr","eng","physique","java","systeme","archi","reseau" ,"classe"};//"Matiere
        final JTable jt = new JTable(data, columnNames);
        jt.setBounds(30, 40, 200, 300);


        JScrollPane sp = new JScrollPane(jt);
        f.add(sp);
        f.setSize(500, 200);
        f.setVisible(true);
    }


}
