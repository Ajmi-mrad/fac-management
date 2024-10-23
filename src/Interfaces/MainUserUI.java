package Interfaces;

import javax.swing.*;
import java.awt.*;


public class MainUserUI extends JFrame {

    JButton SignInButton,loginButton, exitButton;

    public MainUserUI(){
        this.setSize(500, 500);
        this.setTitle("Welcome");
        this.setVisible(true);

        initializeComponents();
        createLayout();
        addEventListeners();
    }


    private void initializeComponents() {
        Font labelFont = new Font("Arial", Font.BOLD, 14);

        loginButton = new JButton("Login");
        loginButton.setFont(labelFont);
        loginButton.setBackground(new Color(100, 153, 233));
        loginButton.setForeground(Color.WHITE);


        SignInButton = new JButton("Cree Compte");
        SignInButton.setFont(labelFont);
        SignInButton.setBackground(new Color(81, 122, 117));
        SignInButton.setForeground(Color.WHITE);

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
        JLabel titleLabel = new JLabel("Bienvenue Chez IssatSo Intern Programme !");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        ImageIcon icon = new ImageIcon("assest/images.jpg"   );
        JLabel label = new JLabel(icon, JLabel.CENTER);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        panel.add(label, c);

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        FlowLayout f = new FlowLayout();
        f.setHgap(10);
        f.setVgap(30);
        titlePanel.setLayout(f);
        this.add(titlePanel, BorderLayout.NORTH);
        titlePanel.add(titleLabel);



        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;

        panel.add(loginButton, c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 3;
        panel.add(SignInButton, c);

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 3;
        panel.add(exitButton, c);

        this.add(panel);
    }
    private void addEventListeners() {
        loginButton.addActionListener(e -> {
            new LoginUI();
        });

        SignInButton.addActionListener(e -> {
            new SignInUI();
        });

        exitButton.addActionListener(e -> {
            System.exit(0);
        });

    }

}
