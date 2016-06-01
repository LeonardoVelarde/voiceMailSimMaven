package Model.ConnectionStates;


import Controller.ConnectionController;
import Database.ContactMapper;
import Database.MySQLConnection;
import Model.Contact;

import java.sql.SQLException;
import java.util.List;

public class ContactMenuState implements ConnectionState {

    private String getContacts(){
        String contactList = "";
        List<Contact> contacts = ContactMapper.getDBContacts();
        for(Contact c : contacts){
            contactList += (c.getNumberAndName() + "\n");
        }
        return contactList;
    }

    public void operate(String key, ConnectionController connectionController) {
        if (key.equals("1"))
        {
            connectionController.state = new ShowContactsState();
            connectionController.speakToAllInterfaces(getContacts() + "Enter 1 to go back\n");
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
            connectionController.speakToAllInterfaces( getContacts() + "Insert 'FirstName SecondName PhoneNumber contactId'");
            connectionController.state = new EditContactState();
        }
        else if (key.equals("5"))
        {
            connectionController.speakToAllInterfaces(ConnectionController.INITIAL_PROMPT);
            connectionController.state = new ConnectedState();
        }
    }


    public void record(String voice, ConnectionController connectionController) {}


    public void hangup(ConnectionController connectionController) {}
}
