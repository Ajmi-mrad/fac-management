package Interfaces;

import dataBase.FetchEtudiant;
import dataBase.StoreEtudiant;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;


public class DeleteEtudiantUI extends JFrame {
    JTextField idEtudiant;
    JButton supprimerButton, annulerButton;

    public DeleteEtudiantUI(){
        this.setSize(500, 600);
        this.setTitle("Supprime un Etudiant");
        this.setVisible(true);

        initializeComponents();
        createLayout();
        addEventListeners();
    }


    private void initializeComponents() {
        Font labelFont = new Font("Arial", Font.BOLD, 14);

        idEtudiant = new JTextField(15);



        supprimerButton = new JButton("Supprimer");

        supprimerButton.setFont(labelFont);
        supprimerButton.setBackground(new Color(0, 153, 204));
        supprimerButton.setForeground(Color.WHITE);

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
        JLabel titleLabel = new JLabel("Suppression d'un Etudiant");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(titleLabel);

        this.add(titlePanel, BorderLayout.NORTH);
        addField(panel, "Id", idEtudiant, c, 1);
        clearInputs();
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;

        panel.add(supprimerButton, c);

        c.gridx = 0;
        c.gridy = 3;
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
    private void addEventListeners() {
        supprimerButton.addActionListener(e -> {
            int idEtudiantText = Integer.parseInt(idEtudiant.getText());

            if(idEtudiantText == 0){
                JOptionPane.showMessageDialog(this, "Champs invalides", "Erreur", JOptionPane.ERROR_MESSAGE);
                return ;
            }
            try {
                String[][] idEtudiant = new FetchEtudiant().CheckifEtudiantExist(idEtudiantText);
                if (idEtudiant[0][0] == null){
                    JOptionPane.showMessageDialog(this, "Etudiant n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return ;
                }
                new StoreEtudiant().deleteEtudiant(idEtudiantText);
                JOptionPane.showMessageDialog(this, "Suppression Etudiant avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
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

    }
}

