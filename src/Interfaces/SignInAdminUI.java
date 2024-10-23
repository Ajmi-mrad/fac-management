package Interfaces;

import Classes.Admin;
import dataBase.StoreAdmin;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;


public class SignInAdminUI extends JFrame {
    JTextField mailAdmin ,nomAdmin,prenomAdmin,passwordAdmin, confirmPassAdmin;
    JButton SignInButton, annulerButton;

    public  SignInAdminUI(){
        this.setSize(500, 500);
        this.setTitle("Sign In Admin" );
        this.setVisible(true);

        initializeComponents();
        createLayout();
        addEventListeners();
    }


    private void initializeComponents() {
        Font labelFont = new Font("Arial", Font.BOLD, 14);

        mailAdmin = new JTextField(15);
        nomAdmin = new JTextField(15);
        prenomAdmin = new JTextField(15);
        passwordAdmin = new JTextField(20);
        confirmPassAdmin = new JTextField(20);


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

        addField(panel, "Mail", mailAdmin, c, 1);
        addField(panel, "Nom", nomAdmin, c, 2);
        addField(panel, "Prenom", prenomAdmin, c, 3);
        addField(panel, "Password", passwordAdmin, c, 4);
        addField(panel, "Confirm Password", confirmPassAdmin, c, 5);

        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 3;

        panel.add(SignInButton, c);

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

    private boolean VerifMail(String mail){
        return mail.contains("@") && mail.contains(".") ;
    }
    private boolean MatchPassword(String password , String confirmPass){
        return password.equals(confirmPass);
    }
    private void addEventListeners() {
        SignInButton.addActionListener(e -> {
            String mail = mailAdmin.getText();
            String nom = nomAdmin.getText();
            String prenom = prenomAdmin.getText();
            String password = passwordAdmin.getText();
            String confirmPass = confirmPassAdmin.getText();
            if (mail.isEmpty() || password.isEmpty() || nom.isEmpty() || prenom.isEmpty()|| confirmPass.isEmpty()) {
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
            Admin admin = new Admin(nom,prenom,mail,password);
            try {
                new StoreAdmin().StoreData(admin);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            new AdminUI();

        });

        annulerButton.addActionListener(e -> {
            clearInputs();
        });
    }

    private void clearInputs() {
        mailAdmin.setText("");
        nomAdmin.setText("");
        prenomAdmin.setText("");
        passwordAdmin.setText("");
        confirmPassAdmin.setText("");

    }
}

