package Controller.ConnectionStates;


import Controller.Connection;
import Model.Contact;

public class DeleteContactState implements ConnectionState{
    public void operate(String key, Connection connection) {
        if (key.equals("#"))
        {
            Contact.deleteContact(Integer.parseInt(connection.accumulatedKeys.split("")[connection.accumulatedKeys.length()-1]));
            connection.speakToAllInterfaces("Contact deleted, type 'Back' to go back.");
        }
        else if(key.equals("Back")){
            connection.speakToAllInterfaces(Connection.CONTACT_MENU_TEXT);
            connection.state = new ContactMenuState();
        }
        else
            connection.accumulatedKeys += key;
    }


    public void record(String voice, Connection connection) {}


    public void hangup(Connection connection) {}
}
