package Controller.ConnectionStates;
import Controller.Connection;

public class RecordingState implements ConnectionState {


    public void operate(String key, Connection connection) {
        if (key.equals("#"))
        {
            if (connection.currentMailbox.checkPasscode(connection.accumulatedKeys))
            {
                connection.state = new MailBoxMenuState();
                connection.interfaceManager.speakToAllInterfaces(connection.MAILBOX_MENU_TEXT);
            }
            else
                connection.interfaceManager.speakToAllInterfaces("Incorrect passcode. Try again!");
            connection.accumulatedKeys = "";
        }
        else
            connection.accumulatedKeys += key;
    }


    public void record(String voice, Connection connection) {}


    public void hangup(Connection connection) {}
}
