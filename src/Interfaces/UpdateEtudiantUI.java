package Interfaces;

import dataBase.FetchEtudiant;
import dataBase.StoreEtudiant;

import javax.swing.*;
import java.awt.*;


public class UpdateEtudiantUI extends JFrame {
    JTextField idEtudiant , nomEtudiant , prenomEtudiant,dateNaissanceEtudiant, classeEtudiant;
    JButton uploadButton, updateButton,annulerButton;
    JComboBox comboBox;


    public UpdateEtudiantUI(){
        this.setSize(500, 600);
        this.setTitle("Modif d'un Etudiant");
        this.setVisible(true);

        initializeComponents();
        createLayout();
        addEventListeners();
    }


    private void initializeComponents() {
        Font labelFont = new Font("Arial", Font.BOLD, 14);

        idEtudiant = new JTextField(15);
        nomEtudiant = new JTextField(15);
        prenomEtudiant = new JTextField(15);
        dateNaissanceEtudiant = new JTextField(15);
        classeEtudiant = new JTextField(15);


        uploadButton = new JButton("Chercher");
        updateButton = new JButton("Modifier");

        updateButton.setFont(labelFont);
        updateButton.setBackground(new Color(0, 153, 204));
        updateButton.setForeground(Color.WHITE);

        uploadButton.setFont(labelFont);
        uploadButton.setBackground(new Color(0, 153, 204));
        uploadButton.setForeground(Color.WHITE);

        annulerButton = new JButton("Annuler");
        annulerButton.setFont(labelFont);
        annulerButton.setBackground(new Color(204, 0, 0));
        annulerButton.setForeground(Color.WHITE);


    }

    private void createLayout() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 20, 5, 10);
        JLabel titleLabel = new JLabel("Modification Etudiant");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(titleLabel);
        String [] classe = {"2PrepaGr1","2PrepaGr2"};
        comboBox = new JComboBox(classe);


        this.add(titlePanel, BorderLayout.NORTH);
        addField(panel, "Id", idEtudiant, c, 1);
        addField(panel, "Nom ", nomEtudiant, c, 3);
        addField(panel, "Prenom", prenomEtudiant, c, 4);
        addField(panel, "Date de Naissance", dateNaissanceEtudiant, c, 5);
        addField(panel, "Classe", comboBox, c, 6);

        clearInputs();
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        panel.add(uploadButton, c);

        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 3;

        panel.add(updateButton, c);

        c.gridx = 0;
        c.gridy = 8;
        c.gridwidth = 3;
        panel.add(annulerButton, c);

        this.add(panel);
    }

    private void addField(JPanel panel, String labelText, JComponent component, GridBagConstraints c, int yPos) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        c.gridx = 0;
        c.gridy = yPos;
        c.gridwidth = 1;
        panel.add(label, c);
        c.gridx = 1;
        c.gridy = yPos;
        c.gridwidth = 2;
        panel.add(component, c);
    }

    private void addEventListeners( ) {
        updateButton.addActionListener(e -> {
            int idEtudiantText = Integer.parseInt(idEtudiant.getText());
            String nomEtudiantText = nomEtudiant.getText();
            String prenomEtudiantText = prenomEtudiant.getText();
            String dateNaissanceEtudiantText = dateNaissanceEtudiant.getText();
            String classeEtudiantText = comboBox.getSelectedItem().toString();

            // verifer si etudiant existe
            try {
                String[][] idEtudiant = new FetchEtudiant().CheckifEtudiantExist(idEtudiantText);
                if (idEtudiant[0][0] == null){
                    JOptionPane.showMessageDialog(this, "Etudiant n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                new StoreEtudiant().updateEtudiant(idEtudiantText,nomEtudiantText,prenomEtudiantText,dateNaissanceEtudiantText,classeEtudiantText);
                JOptionPane.showMessageDialog(this, "Etudiant modifié avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);

                clearInputs();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });
        uploadButton.addActionListener(e -> {
            int idEtudiantText = Integer.parseInt(idEtudiant.getText());
            // verifer si etudiant existe
            try {
                String[][] idEtudiant = new FetchEtudiant().CheckifEtudiantExist(idEtudiantText);
                if (idEtudiant[0][0] == null){
                    JOptionPane.showMessageDialog(this, "Etudiant n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                String [][] data = new FetchEtudiant().fetchNomPrenomDateNaissanceClasseEtudiant(idEtudiantText);
                nomEtudiant.setText(data[0][0]);
                prenomEtudiant.setText(data[0][1]);
                dateNaissanceEtudiant.setText(data[0][2]);
                String studentClass = data[0][3];
                int selectedIndex = -1;
                for (int i = 0; i < comboBox.getItemCount(); i++) {
                    if (comboBox.getItemAt(i).equals(studentClass)) {
                        selectedIndex = i;
                        break;
                    }
                }
                if (selectedIndex != -1) {
                    comboBox.setSelectedIndex(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(this, "Classe de l'étudiant n'est pas dans la liste", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
               } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        annulerButton.addActionListener(e -> {
            clearInputs();
        });
    }

    private void clearInputs() {
        idEtudiant.setText("0");
        nomEtudiant.setText("");
        prenomEtudiant.setText("");
        dateNaissanceEtudiant.setText("");

    }
}

