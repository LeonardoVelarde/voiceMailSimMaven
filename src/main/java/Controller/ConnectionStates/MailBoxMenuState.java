package Controller.ConnectionStates;

import Controller.Connection;

public class MailBoxMenuState implements ConnectionState {


    public void operate(String key, Connection connection) {
        if (key.equals("1"))
        {
            connection.state = Connection.MESSAGE_MENU;
            connection.state2 = new MessageMenuState();
            connection.speakToAllInterfaces(Connection.MESSAGE_MENU_TEXT);
        }
        else if (key.equals("2"))
        {
            connection.state = Connection.CHANGE_PASSCODE;
            connection.state2 = new ChangePasscodeState();
            connection.speakToAllInterfaces("Enter new passcode followed by the # key");
        }
        else if (key.equals("3"))
        {
            connection.state = Connection.CHANGE_GREETING;
            connection.state2 = new ChangeGrettingState();
            connection.speakToAllInterfaces("Record your greeting, then press the # key");
        }
    }


    public void record(String voice, Connection connection) {}


    public void hangup(Connection connection) {}
}
