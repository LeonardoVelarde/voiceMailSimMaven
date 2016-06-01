package Model.ConnectionStates;


import Controller.ConnectionController;
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
        }
        return contactList;
    }

    public void operate(String key, ConnectionController connectionController) {
        if (key.equals("1"))
        {
            connectionController.state = new ShowContactsState();
            String contactList = getContacts();
            connectionController.speakToAllInterfaces(contactList + "Enter 1 to go back\n");
        }
        else if (key.equals("2"))
        {
            connectionController.speakToAllInterfaces("Insert 'FirstName SecondName PhoneNumber'");
            connectionController.state = new NewContactState();

        }
        else if (key.equals("3"))
        {
            connectionController.state = new DeleteContactState();
            String contactList = getContacts();
            connectionController.speakToAllInterfaces(contactList + "Press or type the number of the contact you want to delete it, send 'Back' to go to contact menu.");
        }
        else if (key.equals("4"))
        {
            connectionController.speakToAllInterfaces(ConnectionController.INITIAL_PROMPT);
            connectionController.state = new ConnectedState();
        }
    }


    public void record(String voice, ConnectionController connectionController) {}


    public void hangup(ConnectionController connectionController) {}
}
