package Interfaces;

import Classes.Prof;
import dataBase.StoreProf;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;


public class SignInUI extends JFrame {
    JTextField mailProf ,nomProf,prenomProf,matiereProf,passwordProf, confirmPassProf;
    JComboBox comboBox;
    JButton SignInButton, annulerButton;

    public  SignInUI(){
        this.setSize(500, 500);
        this.setTitle("Sign In" );
        this.setVisible(true);

        initializeComponents();
        createLayout();
        addEventListeners();
    }


    private void initializeComponents() {
        Font labelFont = new Font("Arial", Font.BOLD, 14);

        mailProf = new JTextField(15);
        nomProf = new JTextField(15);
        prenomProf = new JTextField(15);
        matiereProf = new JTextField(15);
        passwordProf = new JTextField(20);
        confirmPassProf = new JTextField(20);


        SignInButton = new JButton("Sign In");
        SignInButton.setFont(labelFont);
        SignInButton.setBackground(new Color(15, 91, 220));
        SignInButton.setForeground(Color.WHITE);


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
        JLabel titleLabel = new JLabel("creer un compte");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(titleLabel);
        this.add(titlePanel, BorderLayout.NORTH);

        String [] matiere = {"Math","Fr","Eng","Physique","Java","Systeme","Archi","Reseau"};
        comboBox = new JComboBox(matiere);

        addField(panel, "Mail", mailProf, c, 1);
        addField(panel, "Nom", nomProf, c, 2);
        addField(panel, "Prenom", prenomProf, c, 3);
        addField(panel, "Matiere", comboBox, c, 4);
        addField(panel, "Password", passwordProf, c, 5);
        addField(panel, "Confirm Password", confirmPassProf, c, 6);

        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 3;

        panel.add(SignInButton, c);

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

    private boolean VerifMail(String mail){
        return mail.contains("@") && mail.contains(".") ;
    }
    private boolean MatchPassword(String password , String confirmPass){
        return password.equals(confirmPass);
    }
    private void addEventListeners() {
        SignInButton.addActionListener(e -> {
            String mail = mailProf.getText();
            String nom = nomProf.getText();
            String prenom = prenomProf.getText();
            String matiere = comboBox.getSelectedItem().toString();
            String password = passwordProf.getText();
            String confirmPass = confirmPassProf.getText();
            if (mail.isEmpty() || password.isEmpty() || nom.isEmpty() || prenom.isEmpty() || matiere.isEmpty() || confirmPass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!VerifMail(mail)) {
                JOptionPane.showMessageDialog(this, "Mail invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!MatchPassword(password,confirmPass)) {
                JOptionPane.showMessageDialog(this, "Password doesn't match", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Prof prof = new Prof(nom,prenom,mail,password,matiere);
            try {
                new StoreProf().StoreData(prof);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            new UserUi(matiere);

        });

        annulerButton.addActionListener(e -> {
            clearInputs();
        });
    }

    private void clearInputs() {
        mailProf.setText("");
        nomProf.setText("");
        prenomProf.setText("");
        matiereProf.setText("");
        passwordProf.setText("");
        confirmPassProf.setText("");

    }
}

