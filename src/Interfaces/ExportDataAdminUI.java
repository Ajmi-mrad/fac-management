package Interfaces;

import dataBase.FetchEtudiant;
import  java.io.*;
import java.sql.SQLException;

public class ExportDataAdminUI {
    public ExportDataAdminUI() throws SQLException, IOException {

        //file path should in desktop .
        String csvFilePath ="Etudiant-Data-Moy.csv";
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));
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
        // export datawith moy to csv file
        String columnNames = "IdEtudiant,NometEtudiant,PrenomEtudiant,DateNaissance,Moyenne,Classe"+"\n";
        fileWriter.write(columnNames);
        for(int i = 0; i < dataWithMoy.length; i++){
            String line = "";
            for(int j = 0; j < dataWithMoy[i].length; j++){
                if (dataWithMoy[i][j] == null) {
                    dataWithMoy[i][j] = " ";
                }
                line += dataWithMoy[i][j] + ",";
            }
            fileWriter.write(line + "\n");
        }

        fileWriter.close();
    }

}