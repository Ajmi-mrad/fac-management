package Interfaces;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;


public class AdminUI extends JFrame {

    JButton inscriptionButton,fetchButton,updateButton,CalculMoyenne,deleteButton, exportDataButton,exitButton;

    public  AdminUI(){
        this.setSize(500, 500);
        this.setTitle("Main ");
        this.setVisible(true);

        initializeComponents();
        createLayout();
        addEventListeners();
    }


    private void initializeComponents() {
        Font labelFont = new Font("Arial", Font.BOLD, 14);

        inscriptionButton = new JButton("Ajoute d'un Etudiant");
        inscriptionButton.setFont(labelFont);
        inscriptionButton.setBackground(new Color(100, 153, 233));
        inscriptionButton.setForeground(Color.WHITE);


        fetchButton = new JButton("Afficher les Etudiants");
        fetchButton.setFont(labelFont);
        fetchButton.setBackground(new Color(85, 134, 161));
        fetchButton.setForeground(Color.WHITE);

        updateButton = new JButton("Update Etudiant");
        updateButton.setFont(labelFont);
        updateButton.setBackground(new Color(81, 122, 117));
        updateButton.setForeground(Color.WHITE);

        CalculMoyenne = new JButton("Calcul Moyenne Etudiant");
        CalculMoyenne.setFont(labelFont);
        CalculMoyenne.setBackground(new Color(32, 112, 129));
        CalculMoyenne.setForeground(Color.WHITE);

        exportDataButton = new JButton("Export Etudiant Data CSV");
        exportDataButton.setFont(labelFont);
        exportDataButton.setBackground(new Color(16, 86, 100));
        exportDataButton.setForeground(Color.WHITE);

        deleteButton = new JButton("Delete Etudiant");
        deleteButton.setFont(labelFont);
        deleteButton.setBackground(new Color(11, 57, 66));
        deleteButton.setForeground(Color.WHITE);

        exitButton = new JButton("Exit");
        exitButton.setFont(labelFont);
        exitButton.setBackground(new Color(204, 0, 0));
        exitButton.setForeground(Color.WHITE);


    }

    private void createLayout() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 20, 5, 10);
        JLabel titleLabel = new JLabel("Bienvenue ! ");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        FlowLayout f = new FlowLayout();
        f.setHgap(10);
        f.setVgap(30);
        titlePanel.setLayout(f);
        this.add(titlePanel, BorderLayout.NORTH);
        titlePanel.add(titleLabel);



        addButtons(c,0,0,3,panel,inscriptionButton);
        addButtons(c,0,1,3,panel,fetchButton);
        addButtons(c,0,2,3,panel,updateButton);
        addButtons(c,0,3,3,panel,CalculMoyenne);
        addButtons(c,0,4,3,panel,exportDataButton);
        addButtons(c,0,5,3,panel,deleteButton);
        addButtons(c,0,6,3,panel,exitButton);

        this.add(panel);
    }
    private void addButtons(GridBagConstraints c,int y,int x,int w,JPanel panel, JButton button){
        c.gridx = y;
        c.gridy = x;
        c.gridwidth = w;
        panel.add(button, c);

    }
    private void addEventListeners() {
        inscriptionButton.addActionListener(e -> {
            new InscriptionEtudiantUI();
        });

        fetchButton.addActionListener(e -> {
            try {
                new DisplayAllEtudiantUI();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        updateButton.addActionListener(e -> {
            new UpdateEtudiantUI();
        });
        CalculMoyenne.addActionListener(e -> {
                    try {
                        new CalculMoyUI();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                });
        exportDataButton.addActionListener(e -> {
            try {
                new ExportDataAdminUI();
                JOptionPane.showMessageDialog(null, "Data Exported Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        deleteButton.addActionListener(e -> {
            new DeleteEtudiantUI();
        });

        exitButton.addActionListener(e -> {
            System.exit(0);
        });

    }

}

