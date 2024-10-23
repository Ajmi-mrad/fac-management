    package Interfaces;

    import dataBase.fetchProf;

    import javax.swing.*;
    import java.awt.*;
    import java.sql.SQLException;


    public class LoginUI extends JFrame {
        JTextField mailProf, passwordProf   ;
        JButton loginButton, annulerButton;

        public  LoginUI(){
            this.setSize(500, 500);
            this.setTitle("Login Prof");
            this.setVisible(true);

            initializeComponents();
            createLayout();
            addEventListeners();
        }


        private void initializeComponents() {
            Font labelFont = new Font("Arial", Font.BOLD, 14);

            mailProf = new JTextField(15);
            passwordProf = new JTextField(20);





            loginButton = new JButton("Login");
            loginButton.setFont(labelFont);
            loginButton.setBackground(new Color(15, 91, 220));
            loginButton.setForeground(Color.WHITE);


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
            JLabel titleLabel = new JLabel("Login");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

            JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            titlePanel.add(titleLabel);

            this.add(titlePanel, BorderLayout.NORTH);

            addField(panel, "Mail", mailProf, c, 1);
            addField(panel, "Password", passwordProf, c, 2);


            c.gridx = 0;
            c.gridy = 3;
            c.gridwidth = 3;

            panel.add(loginButton, c);

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

        private boolean VerifMail(String mail){
            return mail.contains("@") && mail.contains(".") ;
        }
        private void addEventListeners() {
            loginButton.addActionListener(e -> {
                String mail = mailProf.getText();
                String password = passwordProf.getText();

                if (mail.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!VerifMail(mail)) {
                    JOptionPane.showMessageDialog(this, "Mail invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    String [][] matiere = new fetchProf().CheckifProfExist(mail,password);
                    if (matiere[0][0] == null) {
                        JOptionPane.showMessageDialog(this, "Mail ou mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    new UserUi(matiere[0][0]);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }   ;
            });

            annulerButton.addActionListener(e -> {
                clearInputs();
            });
        }

        private void clearInputs() {
            mailProf.setText("");
            passwordProf.setText("");

        }
    }

