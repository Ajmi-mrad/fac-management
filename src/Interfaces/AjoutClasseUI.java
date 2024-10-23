package Interfaces;

import javax.swing.*;
import java.awt.*;


public class AjoutClasseUI extends JFrame {
    JTextField  classe;
    JButton ajouterButton, annulerButton;

    public AjoutClasseUI(){
        this.setSize(500, 600);
        this.setTitle("Ajout d'un classe");
        this.setVisible(true);

        initializeComponents();
        createLayout();
        addEventListeners();
    }


    private void initializeComponents() {
        Font labelFont = new Font("Arial", Font.BOLD, 14);

        classe = new JTextField(15);



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
        JLabel titleLabel = new JLabel("Ajout Classe");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(titleLabel);

        this.add(titlePanel, BorderLayout.NORTH);
        addField(panel, "Classe", classe, c, 1);
        clearInputs();
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;

        panel.add(ajouterButton, c);

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
        ajouterButton.addActionListener(e -> {
            String classeText = classe.getText();

            if(classeText.isEmpty()){
                JOptionPane.showMessageDialog(this, "Champs invalides", "Erreur", JOptionPane.ERROR_MESSAGE);
                return ;
            }

        });

        annulerButton.addActionListener(e -> {
            clearInputs();
        });
    }

    private void clearInputs() {
        classe.setText("");

    }
}

