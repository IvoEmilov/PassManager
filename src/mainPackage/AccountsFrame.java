package mainPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AccountsFrame extends JFrame {

    ArrayList<String> accountsList = new ArrayList<>();
    GridLayout layout = new GridLayout(5,1);
    JButton addAccountBtn = new JButton("Add new account");
    JButton removeAccountBtn = new JButton("Remove account");
    JButton showPassBtn = new JButton("Show details");
    JButton logoutBtn = new JButton("Logout");
    static JComboBox accounts = new JComboBox();
    AccountsFrame(int userID){
        this.setTitle("Accounts");
        this.setSize(new Dimension(250,400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(layout);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        addAccountBtn.setFocusable(false);
        removeAccountBtn.setFocusable(false);
        showPassBtn.setFocusable(false);
        logoutBtn.setFocusable(false);

        accountsList = MySQLDriver.getAllAccounts(userID);
        for(String acc : accountsList){
            accounts.addItem(acc);
        }

        this.add(accounts);
        this.add(showPassBtn);
        this.add(addAccountBtn);
        this.add(removeAccountBtn);
        this.add(logoutBtn);

        addAccountBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddNewAccountFrame(userID);
            }
        });
        removeAccountBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MySQLDriver.deleteAccount((String)accounts.getSelectedItem(),userID);
            }
        });
        showPassBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MySQLDriver.getRequestedAccount((String)accounts.getSelectedItem(),userID);
            }
        });

        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accounts.removeAllItems();
                accountsList.clear();
                closeFrame();
                new LoginFrame();
            }
        });
    }
    private void closeFrame(){
        this.dispose();
    }
}
