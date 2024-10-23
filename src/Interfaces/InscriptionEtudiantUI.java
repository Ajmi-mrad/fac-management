package Interfaces;

import Classes.Etudiant;
import dataBase.FetchEtudiant;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;


public class InscriptionEtudiantUI extends JFrame {
    JTextField idEtudiant , nomEtudiant , prenomEtudiant,dateNaissanceEtudiant , classeEtudiant;
    JButton ajouterButton, annulerButton;
    JComboBox comboBox;
    public InscriptionEtudiantUI(){
        this.setSize(500, 600);
        this.setTitle("Ajout d'un Etudiant");
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






        ajouterButton = new JButton("Ajouter");

        ajouterButton.setFont(labelFont);
        ajouterButton.setBackground(new Color(0, 153, 204));
        ajouterButton.setForeground(Color.WHITE);

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
        JLabel titleLabel = new JLabel("Inscription Etudiant");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(titleLabel);
        String [] classe = {"2PrepaGr1","2PrepaGr2"};
        comboBox = new JComboBox(classe);

        this.add(titlePanel, BorderLayout.NORTH);
        addField(panel, "Id", idEtudiant, c, 1);
        addField(panel, "Nom ", nomEtudiant, c, 2);
        addField(panel, "Prenom", prenomEtudiant, c, 3);
        addField(panel, "Date de Naissance", dateNaissanceEtudiant, c, 4);
        addField(panel, "Classe", comboBox, c, 5);

        clearInputs();
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 3;

        panel.add(ajouterButton, c);

        c.gridx = 0;
        c.gridy = 7;
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
        ajouterButton.addActionListener(e -> {
            int idEtudiantText = Integer.parseInt(idEtudiant.getText());
            String nomEtudiantText = nomEtudiant.getText();
            String prenomEtudiantText = prenomEtudiant.getText();
            String dateNaissanceEtudiantText = dateNaissanceEtudiant.getText();
            String classeEtudiantText = comboBox.getSelectedItem().toString();

            if (nomEtudiantText.isEmpty() || prenomEtudiantText.isEmpty() || dateNaissanceEtudiantText.isEmpty() || classeEtudiantText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // verifer si etudiant existe
            try {
                String[][] idEtudiant = new FetchEtudiant().CheckifEtudiantExist(idEtudiantText);
                if (idEtudiant[0][0] != null){
                    JOptionPane.showMessageDialog(this, "Etudiant Déja Existe", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return ;
                }
                new Etudiant(idEtudiantText,nomEtudiantText,prenomEtudiantText,dateNaissanceEtudiantText,classeEtudiantText);
                JOptionPane.showMessageDialog(this, "Etudiant ajoutée avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
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

    }
}

