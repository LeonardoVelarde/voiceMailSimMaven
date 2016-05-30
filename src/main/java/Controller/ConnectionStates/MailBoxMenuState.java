package Controller.ConnectionStates;

import Controller.Connection;

public class MailBoxMenuState implements ConnectionState {


    public void operate(String key, Connection connection) {
        if (key.equals("1"))
        {
            connection.state = new MessageMenuState();
            connection.speakToAllInterfaces(Connection.MESSAGE_MENU_TEXT);
        }
        else if (key.equals("2"))
        {
            connection.state = new ChangePasscodeState();
            connection.speakToAllInterfaces("Enter new passcode followed by the # key");
        }
        else if (key.equals("3"))
        {
            connection.state = new ChangeGreetingState();
            connection.speakToAllInterfaces("Record your greeting, then press the # key");
        }
    }


    public void record(String voice, Connection connection) {}


    public void hangup(Connection connection) {}
}
