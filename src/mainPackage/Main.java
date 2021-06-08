package mainPackage;

public class Main {
    public static void main(String[] args){

        MySQLDriver.createDatabase();
        MySQLDriver.createTables();
        new LoginFrame();
    }
}
