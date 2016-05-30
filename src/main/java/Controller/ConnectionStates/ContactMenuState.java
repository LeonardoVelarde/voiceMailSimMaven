package Controller.ConnectionStates;


import Controller.Connection;
import Database.MySQLConnection;
import Model.Contact;

import java.sql.SQLException;
import java.util.List;

public class ContactMenuState implements ConnectionState {

    private String getContacts(){
        String contactList = "";
        List<Contact> contacts;
        try {
            MySQLConnection myconn = new MySQLConnection();
            contacts = myconn.getContactList();
            for(Contact c : contacts){
                contactList += (c.getNumberAndName() + "\n");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return contactList;
    }

    public void operate(String key, Connection connection) {
        if (key.equals("1"))
        {
            connection.state = new ShowContactsState();
            String contactList = getContacts();
            connection.speakToAllInterfaces(contactList + "Enter 1 to go back\n");
        }
        else if (key.equals("2"))
        {
            connection.speakToAllInterfaces("Insert 'FirstName SecondName PhoneNumber'");
            connection.state = new NewContactState();

        }
        else if (key.equals("3"))
        {
            connection.state = new DeleteContactState();
            String contactList = getContacts();
            connection.speakToAllInterfaces(contactList + "Press or type the number of the contact you want to delete it, send 'Back' to go to contact menu.");
        }
        else if (key.equals("4"))
        {
            connection.speakToAllInterfaces(Connection.INITIAL_PROMPT);
            connection.state = new ConnectedState();
        }
    }


    public void record(String voice, Connection connection) {}


    public void hangup(Connection connection) {}
}
