package mainPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;

public class LoginFrame extends JFrame {
    GridLayout layout = new GridLayout(7,1);
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginBtn = new JButton("Login");
    JButton registerBtn = new JButton("Register");
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

        loginBtn.setFocusable(false);
        registerBtn.setFocusable(false);

        invalidLogin.setForeground(Color.red);
        invalidLogin.setHorizontalAlignment(JLabel.CENTER);
        invalidLogin.setVisible(false);

        this.add(usernameLabel);
        this.add(usernameField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(loginBtn);
        this.add(registerBtn);
        this.add(invalidLogin);

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int userID  = MySQLDriver.loginUser(usernameField.getText(),Sha256.toHexString(Sha256.getSHA(String.valueOf(passwordField.getPassword()))));
                    if(userID!=-1){
                        new AccountsFrame(userID);
                        closeFrame();
                    }
                } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                    noSuchAlgorithmException.printStackTrace();
                }
            }
        });
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MySQLDriver.registerUserIntoDatabase(usernameField.getText(),Sha256.toHexString(Sha256.getSHA(String.valueOf(passwordField.getPassword()))));
                } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                    noSuchAlgorithmException.printStackTrace();
                }
            }
        });
    }
    private void closeFrame(){
        this.dispose();
    }
}
