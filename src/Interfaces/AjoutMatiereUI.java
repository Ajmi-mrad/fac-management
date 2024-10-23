package Interfaces;

import javax.swing.*;
import java.awt.*;


public class AjoutMatiereUI extends JFrame {
    JTextField  idMatiere;
    JTextField  nomMatiere;
    JTextField  coeffMatiere;
    JButton ajouterButton, annulerButton;

    public AjoutMatiereUI(){
        this.setSize(500, 600);
        this.setTitle("Ajout d'un Matiere");
        this.setVisible(true);

        initializeComponents();
        createLayout();
        addEventListeners();
    }


    private void initializeComponents() {
        Font labelFont = new Font("Arial", Font.BOLD, 14);

        nomMatiere = new JTextField(15);
        coeffMatiere = new JTextField(15);


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
        JLabel titleLabel = new JLabel("Ajout Matiere");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(titleLabel);

        this.add(titlePanel, BorderLayout.NORTH);
        addField(panel, "Nom de Matiere", nomMatiere, c, 1);
        addField(panel, "Coeff de Matiere", coeffMatiere, c, 2);
        clearInputs();

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;

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

    private void addEventListeners() {
        ajouterButton.addActionListener(e -> {
            String matiereText = nomMatiere.getText();
            String coeffMatiereText = coeffMatiere.getText();
            if(matiereText.isEmpty()){
                JOptionPane.showMessageDialog(this, "Champs invalides", "Erreur", JOptionPane.ERROR_MESSAGE);
                return ;
            }
            if(coeffMatiereText.isEmpty()){
                JOptionPane.showMessageDialog(this, "Champs invalides", "Erreur", JOptionPane.ERROR_MESSAGE);
                return ;
            }

        });

        annulerButton.addActionListener(e -> {
            clearInputs();
        });
    }

    private void clearInputs() {
        nomMatiere.setText("");
        coeffMatiere.setText("");

    }
}

