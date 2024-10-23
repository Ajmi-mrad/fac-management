package Interfaces;

import dataBase.FetchEtudiant;
import dataBase.StoreEtudiant;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;


public class UpdateNoteUI extends JFrame {
    JTextField idEtudiant, ancienNote,matiere;
    JButton ajouterButton, annulerButton,uploadButton;

    public UpdateNoteUI(String matiereName){
        this.setSize(500, 600);
        this.setTitle("Update note d'un Etudiant");
        this.setVisible(true);

        initializeComponents();
        createLayout();
        addEventListeners(matiereName);
    }


    private void initializeComponents() {
        Font labelFont = new Font("Arial", Font.BOLD, 14);

        idEtudiant = new JTextField(15);
        matiere = new JTextField(15);
        ancienNote = new JTextField(15);



        ajouterButton = new JButton("Update");

        ajouterButton.setFont(labelFont);
        ajouterButton.setBackground(new Color(0, 153, 204));
        ajouterButton.setForeground(Color.WHITE);

        uploadButton = new JButton("Upload");
        uploadButton.setFont(labelFont);
        uploadButton.setBackground(new Color(70, 211, 26));
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
        c.insets = new Insets(1, 20, 2, 10);
        JLabel titleLabel = new JLabel("Update Note");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        FlowLayout f = new FlowLayout();
        f.setHgap(10);
        f.setVgap(30);
        titlePanel.setLayout(f);
        this.add(titlePanel, BorderLayout.NORTH);
        titlePanel.add(titleLabel);


        addField(panel, "Id", idEtudiant, c, 1);
        addField(panel, "Ancienne Note", ancienNote, c, 2);
        addField(panel, "Note de Matiere", matiere, c, 4);
        clearInputs();
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 3;

        panel.add(ajouterButton, c);

        c.gridx = 3;
        c.gridy = 2;
        c.gridwidth = 2;

        panel.add(uploadButton, c);

        c.gridx = 1;
        c.gridy = 6;
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
        uploadButton.addActionListener(e -> {
            int idEtudiantText = Integer.parseInt(idEtudiant.getText());
            if (idEtudiantText == 0){
                JOptionPane.showMessageDialog(this, "Champs invalides", "Erreur", JOptionPane.ERROR_MESSAGE);
                return ;
            }
            try {
                String[][] idEtudiant = new FetchEtudiant().CheckifEtudiantExist(idEtudiantText);
                if (idEtudiant[0][0] == null){
                    JOptionPane.showMessageDialog(this, "Etudiant n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return ;
                }
                try {
                    uploadNote(idEtudiantText,matiereName);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
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
        ancienNote.setText("0");
        matiere.setText("0");

    }
    private void uploadNote(int idEtudiant, String matiere) throws SQLException {
        int note = new FetchEtudiant().getNoteEtudiant(idEtudiant,matiere);
        ancienNote.setText(String.valueOf(note));
    }
}
