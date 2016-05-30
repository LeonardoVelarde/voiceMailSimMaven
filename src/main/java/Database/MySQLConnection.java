package Database;

import Model.Contact;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLConnection {
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/voicesimulator";

    //  Database credentials
    private static final String USER = "aquanuestra";
    private static final String PASS = "aquanuestra";

    private Connection conn = null;

    public MySQLConnection() throws SQLException, ClassNotFoundException {
        connectWithDB();
    }

    private void connectWithDB() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void runSQL(String sql) throws SQLException{
        System.out.println(sql);
        Statement stmt;
        stmt = conn.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }

    public List<String> getMessagesList() throws SQLException {
        Statement stmt;
        stmt = conn.createStatement();
        String sql = "SELECT content FROM Message";
        ResultSet rs = stmt.executeQuery(sql);
        ArrayList<String> messages = new ArrayList<String>();
        while(rs.next()){
            messages.add(rs.getString("content"));
        }

        rs.close();
        stmt.close();
        return messages;
    }

    public List<Contact> getContactList() throws SQLException {
        Statement stmt;
        stmt = conn.createStatement();
        String sql = "SELECT * FROM Contact";
        ResultSet rs = stmt.executeQuery(sql);

        List<Contact> contacts = new ArrayList<Contact>();
        while(rs.next()){
            contacts.add(new Contact(rs.getString("first_name"), rs.getString("last_name"), rs.getString("phone_number"), rs.getInt("id")));
        }

        rs.close();
        stmt.close();
        return contacts;
    }

    public static void main(String[] args){
//        try{
//            MySQLConnection myconn = new MySQLConnection();
//            List<Contact> te = myconn.getContactList();
//            myconn.closeConnection();
//            for(Contact mess : te){
//                System.out.println(mess.getName());
//            }
//        }
//         catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        Contact c = new Contact("Contacto3", "Apellido3", "3", 5);
        c.save();
    }
}
