package Controller.ConnectionStates;

import Controller.Connection;
import Model.Contact;

public class ConnectedState implements ConnectionState {

    public void operate(String key, Connection connection) {
        if (key.equals("#"))
        {
            connection.currentMailbox = connection.system.findMailbox(connection.accumulatedKeys);
            if (connection.currentMailbox != null)
            {
                connection.state = new RecordingState();
                connection.speakToAllInterfaces(connection.currentMailbox.getGreeting());
            }
            else
                connection.speakToAllInterfaces("Incorrect mailbox number. Try again!");
            connection.accumulatedKeys = "";
        }
        else if(key.equals("Contacts"))
        {
            connection.state = new ContactMenuState();
            connection.speakToAllInterfaces(Connection.CONTACT_MENU_TEXT);
        }
        else
            connection.accumulatedKeys += key;
    }


    public void record(String voice, Connection connection) {}


    public void hangup(Connection connection) {}
}
