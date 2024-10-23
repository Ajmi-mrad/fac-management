package Interfaces;

import dataBase.FetchEtudiant;
import dataBase.StoreEtudiant;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;


public class AjoutNote extends JFrame {
    JTextField idEtudiant, matiere;
    JButton ajouterButton, annulerButton;

    public AjoutNote(String matiereName){
        this.setSize(500, 600);
        this.setTitle("Ajout note d'un Etudiant");
        this.setVisible(true);

        initializeComponents();
        createLayout();
        addEventListeners(matiereName);
    }


    private void initializeComponents() {
        Font labelFont = new Font("Arial", Font.BOLD, 14);

        idEtudiant = new JTextField(15);
        matiere = new JTextField(15);



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
        JLabel titleLabel = new JLabel("Ajout note d'un Etudiant");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        FlowLayout f = new FlowLayout();
        f.setHgap(10);
        f.setVgap(30);
        titlePanel.setLayout(f);
        this.add(titlePanel, BorderLayout.NORTH);
        titlePanel.add(titleLabel);

        this.add(titlePanel, BorderLayout.NORTH);
        addField(panel, "Id", idEtudiant, c, 1);
        addField(panel, "Note de Matiere", matiere, c, 2);
        clearInputs();
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 3;

        panel.add(ajouterButton, c);

        c.gridx = 0;
        c.gridy = 4;
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

    private boolean verifNote(int note){
        return note >= 0 && note <= 20;
    }
    private void addEventListeners(String matiereName ) {
        ajouterButton.addActionListener(e -> {
            int idEtudiantText = Integer.parseInt(idEtudiant.getText());
            int matiereText = Integer.parseInt(matiere.getText());

            if(idEtudiantText == 0 || matiereText == 0){
                JOptionPane.showMessageDialog(this, "Champs invalides", "Erreur", JOptionPane.ERROR_MESSAGE);
                return ;
            }
            if (!verifNote(matiereText)){
                JOptionPane.showMessageDialog(this, "Note invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
                return ;
            }
            try {
                String[][] idEtudiant = new FetchEtudiant().CheckifEtudiantExist(idEtudiantText);
                if (idEtudiant[0][0] == null){
                    JOptionPane.showMessageDialog(this, "Etudiant n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return ;
                }
                new StoreEtudiant().AjouterNotes(idEtudiantText,matiereName,matiereText);
                JOptionPane.showMessageDialog(this, "Note ajoutée avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
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
        matiere.setText("0");

    }
}
