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
    JTextField aboutText = new JTextField();
    JTextField usernameText = new JTextField();
    JPasswordField passwordText = new JPasswordField();
    JButton addButton = new JButton("ADD");
    AddNewAccountFrame(int userID) {
        this.setTitle("Accounts");
        this.setSize(new Dimension(250, 400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(layout);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        addButton.setFocusable(false);

        this.add(aboutLabel);
        this.add(aboutText);
        this.add(usernameLabel);
        this.add(usernameText);
        this.add(passwordLabel);
        this.add(passwordText);
        this.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = MySQLDriver.addAccount(aboutText.getText(),usernameText.getText(),String.valueOf(passwordText.getPassword()),userID);
                if(result==1) {
                    JOptionPane.showMessageDialog(null,"Successfully Added!");
                    closeFrame();
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
