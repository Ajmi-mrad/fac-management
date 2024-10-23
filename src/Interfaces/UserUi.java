package Interfaces;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;


public class UserUi extends JFrame {

    JButton inscriptionButton,fetchButton,updateButton,exportDataButton,exportDataPDFButton, exitButton;

    public  UserUi(String matiere){
        this.setSize(500, 500);
        this.setTitle("Main ");
        this.setVisible(true);

        initializeComponents();
        createLayout();
        addEventListeners(matiere);
    }


    private void initializeComponents() {
        Font labelFont = new Font("Arial", Font.BOLD, 14);

        inscriptionButton = new JButton("Ajouter Note d'un Etudiant");
        inscriptionButton.setFont(labelFont);
        inscriptionButton.setBackground(new Color(100, 153, 233));
        inscriptionButton.setForeground(Color.WHITE);


        fetchButton = new JButton("Afficher les Etudiants");
        fetchButton.setFont(labelFont);
        fetchButton.setBackground(new Color(85, 134, 161));
        fetchButton.setForeground(Color.WHITE);

        updateButton = new JButton("Update Note Etudiant");
        updateButton.setFont(labelFont);
        updateButton.setBackground(new Color(88, 151, 155));
        updateButton.setForeground(Color.WHITE);

        exportDataButton = new JButton("Export Etudiant Data CSV");
        exportDataButton.setFont(labelFont);
        exportDataButton.setBackground(new Color(81, 122, 117));
        exportDataButton.setForeground(Color.WHITE);

        exportDataPDFButton = new JButton("Export Etudiant Data PDF");
        exportDataPDFButton.setFont(labelFont);
        exportDataPDFButton.setBackground(new Color(16, 86, 100));
        exportDataPDFButton.setForeground(Color.WHITE);

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



        addButtons(c,1,0,2,panel,inscriptionButton);
        addButtons(c,1,2,2,panel,updateButton);
        addButtons(c,1,1,2,panel,fetchButton);
        addButtons(c,1,3,2,panel,exportDataButton);
        addButtons(c,1,4,2,panel,exportDataPDFButton);
        addButtons(c,1,5,2,panel,exitButton);

        this.add(panel);
    }
    private void addButtons(GridBagConstraints c,int x,int y,int w,JPanel panel, JButton button){
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = w;
        panel.add(button, c);

    }
    private void addEventListeners(String matiere) {
        inscriptionButton.addActionListener(e -> {
            new AjoutNote(matiere);
        });

        fetchButton.addActionListener(e -> {
            try {
                new DisplayEtudiantUI(matiere);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        updateButton.addActionListener(e -> {
            new UpdateNoteUI(matiere);
        });
        exportDataButton.addActionListener(e -> {
            try {
                new ExportDataUI(matiere);
                JOptionPane.showMessageDialog(null, "Data Exported Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        exportDataPDFButton.addActionListener(e -> {
            try {
                new ExportDataPDFUI(matiere);
                JOptionPane.showMessageDialog(null, "Data Exported Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });


        exitButton.addActionListener(e -> {
            System.exit(0);
        });

    }

}
