package mainPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;

public class LoginFrame extends JFrame {
    GridLayout layout = new GridLayout(7,1);
    JTextField username = new JTextField();
    JPasswordField password = new JPasswordField();
    JButton login = new JButton("Login");
    JButton register = new JButton("Register");
    static JLabel invalidLogin = new JLabel("Invalid username/password.");
    JLabel usernameLabel = new JLabel("Username:");
    JLabel passwordLabel = new JLabel("Password:");
    LoginFrame(){
        layout.setVgap(5);
        this.setTitle("Login");
        this.setSize(new Dimension(250,400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(layout);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        login.setFocusable(false);
        register.setFocusable(false);

        invalidLogin.setForeground(Color.red);
        invalidLogin.setHorizontalAlignment(JLabel.CENTER);
        invalidLogin.setVisible(false);

        this.add(usernameLabel);
        this.add(username);
        this.add(passwordLabel);
        this.add(password);
        this.add(login);
        this.add(register);
        this.add(invalidLogin);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MySQLDriver.loginUser(username.getText(),Sha256.toHexString(Sha256.getSHA(String.valueOf(password.getPassword()))));
                } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                    noSuchAlgorithmException.printStackTrace();
                }
            }
        });
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MySQLDriver.registerUserIntoDatabase(username.getText(),Sha256.toHexString(Sha256.getSHA(String.valueOf(password.getPassword()))));
                } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                    noSuchAlgorithmException.printStackTrace();
                }
            }
        });

    }
}
