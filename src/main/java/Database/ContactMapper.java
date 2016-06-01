package Database;

import Model.Contact;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactMapper {
    public static final String tableName = "Contact";
    public static final String INSERT_STATEMENT = "INSERT INTO " + tableName + " (first_name, last_name, phone_number) VALUES (?, ?, ?);";
    public static final String DELETE_STATEMENT = "DELETE FROM " + tableName + " WHERE id = ? ;";
    public static final String UPDATE_STATEMENT = "UPDATE " + tableName + " SET first_name = ?, last_name = ?, phone_number = ? WHERE id = ? ;";
    public static final String SELECT_STATEMENT = "SELECT * FROM Contact;";

    public static void saveToDB(Contact c){
        String[] insertData = c.getInsertDataArray();
        MySQLConnection mySQLConnection = new MySQLConnection();
        mySQLConnection.runNoQueryStatement(INSERT_STATEMENT, insertData);
        mySQLConnection.closeConnection();
    }

    public static void deleteFromDB(String idToDelete){
        MySQLConnection mySQLConnection = new MySQLConnection();
        mySQLConnection.deleteWithId(DELETE_STATEMENT, Integer.parseInt(idToDelete));
        mySQLConnection.closeConnection();
    }

    public static void updateInDB(Contact c){
        String[] updateData = c.getUpdateDataArray();
        MySQLConnection mySQLConnection = new MySQLConnection();
        mySQLConnection.runNoQueryStatement(UPDATE_STATEMENT, updateData);
        mySQLConnection.closeConnection();
    }

    public static List<Contact> getDBContacts(){
        MySQLConnection mySQLConnection = new MySQLConnection();
        ResultSet contactResultSet = mySQLConnection.runQueryStatement(SELECT_STATEMENT);
        List<Contact> contacts = new ArrayList<Contact>();
        try{
            while(contactResultSet.next()){
                contacts.add(new Contact(contactResultSet.getString("first_name"), contactResultSet.getString("last_name"), contactResultSet.getString("phone_number"), contactResultSet.getInt("id")));
            }

            contactResultSet.close();
        }
        catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
        mySQLConnection.closeConnection();
        return contacts;
    }
}
