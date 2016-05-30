package Controller.ConnectionStates;

import Controller.Connection;

public class ChangeGrettingState implements ConnectionState {

    public void operate(String key, Connection connection) {
        if (key.equals("#"))
        {
            connection.currentMailbox.setGreeting(connection.currentRecording);
            connection.currentRecording = "";
            connection.state = Connection.MAILBOX_MENU;
            connection.interfaceManager.speakToAllInterfaces(Connection.MAILBOX_MENU_TEXT);
        }
    }


    public void record(String voice, Connection connection) {}


    public void hangup(Connection connection) {}
}
