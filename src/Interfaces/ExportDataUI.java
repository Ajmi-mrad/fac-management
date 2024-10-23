package Interfaces;

import dataBase.FetchEtudiant;
import  java.io.*;
import java.sql.SQLException;

public class ExportDataUI {
    public ExportDataUI(String matiere) throws SQLException, IOException {

        //file path should in desktop .
        String csvFilePath ="Etudiant-Data.csv";
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));
        String[][] data = new FetchEtudiant().FetchData(matiere);
        String columnNames = "IdEtudiant,NometEtudiant,PrenomEtudiant,Note+"+matiere+",Classe"+"\n";
        fileWriter.write(columnNames);
        for(int i = 0; i < data.length; i++){
            String line = "";
            for(int j = 0; j < data[i].length; j++){
                if (data[i][j] == null) {
                    data[i][j] = " ";
                }
                line += data[i][j] + ",";
            }
            fileWriter.write(line + "\n");
        }

        fileWriter.close();
    }

}