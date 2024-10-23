package Interfaces;

import javax.swing.*;
import java.awt.*;


public class ChoixUI extends JFrame {

    JButton adminButton,profButton,exitButton;

    public  ChoixUI(){
        this.setSize(500, 500);
        this.setTitle("Main ");
        this.setVisible(true);

        initializeComponents();
        createLayout();
        addEventListeners();
    }


    private void initializeComponents() {
        Font labelFont = new Font("Arial", Font.BOLD, 14);

        adminButton = new JButton("Interface Admin");
        adminButton.setFont(labelFont);
        adminButton.setBackground(new Color(100, 153, 233));
        adminButton.setForeground(Color.WHITE);


        profButton = new JButton("Interface Prof");
        profButton.setFont(labelFont);
        profButton.setBackground(new Color(85, 134, 161));
        profButton.setForeground(Color.WHITE);


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

        panel.add(adminButton, c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 3;
        panel.add(profButton, c);

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 3;
        panel.add(exitButton, c);

        this.add(panel);
    }
    private void addButtons(GridBagConstraints c,int x,int y,int w,JPanel panel, JButton button){
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = w;
        panel.add(button, c);

    }
    private void addEventListeners() {
        adminButton.addActionListener(e -> {
            new MainAdminUI();
        });

        profButton.addActionListener(e -> {
            new MainUserUI();
        });

        exitButton.addActionListener(e -> {
            System.exit(0);
        });

    }

}
