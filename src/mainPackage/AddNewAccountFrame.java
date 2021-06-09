package mainPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNewAccountFrame extends JFrame {

    GridLayout layout = new GridLayout(7,1);
    JLabel aboutLabel = new JLabel("Information about the account:");
    JLabel usernameLabel = new JLabel("Username:");
    JLabel passwordLabel = new JLabel("Password:");
    JTextField aboutField = new JTextField();
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton addBtn = new JButton("ADD");
    AddNewAccountFrame(int userID) {
        this.setTitle("Accounts");
        this.setSize(new Dimension(250, 400));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(layout);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        addBtn.setFocusable(false);

        this.add(aboutLabel);
        this.add(aboutField);
        this.add(usernameLabel);
        this.add(usernameField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(addBtn);

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = MySQLDriver.addAccount(aboutField.getText(),usernameField.getText(),SorgeEncryptor.encrypt(String.valueOf(passwordField.getPassword())),userID);
                if(result==1) {
                    JOptionPane.showMessageDialog(null,"Successfully Added!");
                    AccountsFrame.accounts.addItem(aboutField.getText());
                    closeFrame();
                }
                else if(result==-2){
                    JOptionPane.showMessageDialog(null,"Please fill all fields.","Error",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Sorry, there was an error!","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    private void closeFrame(){
        this.dispose();
    }
}
