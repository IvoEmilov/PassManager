package mainPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;

public class AccountsFrame extends JFrame {

    GridLayout layout = new GridLayout(5,1);
    JButton addAccount = new JButton("Add new account");
    JButton removeAccount = new JButton("Remove account");
    JButton showPass = new JButton("Show details");
    JButton logout = new JButton("Logout");
    JComboBox accounts = new JComboBox();
    AccountsFrame(int userID){
        this.setTitle("Accounts");
        this.setSize(new Dimension(250,400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(layout);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        addAccount.setFocusable(false);
        removeAccount.setFocusable(false);
        showPass.setFocusable(false);
        logout.setFocusable(false);

        logout.setMargin(new Insets(10,25,10,25));

        this.add(accounts);
        this.add(showPass);
        this.add(addAccount);
        this.add(removeAccount);
        this.add(logout);

        addAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddNewAccountFrame(userID);
            }
        });

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeFrame();
                new LoginFrame();
            }
        });
    }
    private void closeFrame(){
        this.dispose();
    }
}
