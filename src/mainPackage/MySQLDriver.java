package mainPackage;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class MySQLDriver {
    //private final String dbName = "PASSWORDMANAGER";

    public static void createDatabase(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "1234");
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate("CREATE DATABASE IF NOT EXISTS PASSWORDMANAGER;");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void createTables(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/passwordmanager", "root", "1234");
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate("CREATE TABLE IF NOT EXISTS USERS(" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "username VARCHAR(255) UNIQUE NOT NULL," +
                    "password VARCHAR(255) NOT NULL);");
            result = statement.executeUpdate("CREATE TABLE IF NOT EXISTS ACCOUNTS(" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "about VARCHAR(255) NOT NULL," +
                    "username VARCHAR(255) NOT NULL," +
                    "password VARCHAR(255) NOT NULL,"+
                    "user_id INT NOT NULL,"+
                    "CONSTRAINT FOREIGN KEY(user_id) REFERENCES USERS(id) ON DELETE CASCADE);");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static int loginUser(String username, String password){
        if(username.equals("")||password.equals("")) return -1;
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/passwordmanager", "root", "1234");
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT id FROM users WHERE username=\""+username+"\" AND password=\""+password+"\";");
            if(result.next()){
                return result.getInt("id");
               // JOptionPane.showMessageDialog(null,"Login successful! ID="+id,"Success",JOptionPane.OK_OPTION);

            }
            else
            {
                LoginFrame.invalidLogin.setForeground(Color.red);
                LoginFrame.invalidLogin.setHorizontalAlignment(JLabel.CENTER);
                LoginFrame.invalidLogin.setVisible(true);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    public static void registerUserIntoDatabase(String username, String password){
        if(username.equals("")||password.equals("")) return;
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/passwordmanager", "root", "1234");
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT username from users where username=\""+username+"\";");
            if(result.next()){
                System.out.println(result.getString("username"));
                JOptionPane.showMessageDialog(null,"User already exists.","Error",JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                int resultInsert = statement.executeUpdate("INSERT INTO USERS (username,password) " +
                        "VALUES ('"+username+"', '"+password+"');");
                if(resultInsert==1){
                    JOptionPane.showMessageDialog(null,"Successfully registered!","Success",JOptionPane.OK_OPTION);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Error!","Error",JOptionPane.OK_OPTION);
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static int addAccount(String about, String username, String password, int userID){
        if(about.equals("")||username.equals("")||password.equals("")) return -2;
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/passwordmanager", "root", "1234");
            Statement statement = connection.createStatement();
            return statement.executeUpdate("INSERT INTO accounts (about, username, password, user_id) " +
                    "VALUES ('"+about+"', '"+username+"', '"+password+"', '"+userID+"');");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }
    public static void deleteAccount(String about, int userID){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/passwordmanager", "root", "1234");
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate("DELETE FROM accounts WHERE about='"+about+"' AND user_id="+userID+";");
            if(result>0){
                AccountsFrame.accounts.removeItem(about);
                JOptionPane.showMessageDialog(null,"Account removed successfully!","Success",JOptionPane.OK_OPTION);
            }
            else{
                JOptionPane.showMessageDialog(null,"Sorry, there was and error!", "Error",JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static ArrayList<String> getAllAccounts(int userID){
       ArrayList<String> accounts = new ArrayList<String>();
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/passwordmanager", "root", "1234");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT about FROM accounts WHERE user_id="+userID+";");
            while(resultSet.next()){
                accounts.add(resultSet.getString("about"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return accounts;
    }
    public static void getRequestedAccount(String aboutQuery, int userID){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/passwordmanager", "root", "1234");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT about,username,password FROM accounts WHERE about='"+aboutQuery+"' and user_id="+userID+";");
            while(resultSet.next()){
                String about = resultSet.getString("about");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                JOptionPane.showMessageDialog(null,about+"\nUsername: "+username+"\nPassword: "+SorgeEncryptor.decrypt(password));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
