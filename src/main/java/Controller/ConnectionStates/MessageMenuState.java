package Controller.ConnectionStates;

import Controller.Connection;
import Model.Message;

public class MessageMenuState implements ConnectionState {

    public void operate(String key, Connection connection) {
        if (key.equals("1"))
        {
            String output = "";
            Message m = connection.currentMailbox.getCurrentMessage();
            if (m == null) output += "No messages." + "\n";
            else output += m.getText() + "\n";
            output += Connection.MESSAGE_MENU_TEXT;
            connection.interfaceManager.speakToAllInterfaces(output);
        }
        else if (key.equals("2"))
        {
            connection.currentMailbox.saveCurrentMessage();
            connection.interfaceManager.speakToAllInterfaces(Connection.MESSAGE_MENU_TEXT);
        }
        else if (key.equals("3"))
        {
            connection.currentMailbox.removeCurrentMessage();
            connection.interfaceManager.speakToAllInterfaces(Connection.MESSAGE_MENU_TEXT);
        }
        else if (key.equals("4"))
        {
            connection.state = Connection.MAILBOX_MENU;
            connection.state2 = new MailBoxMenuState();
            connection.interfaceManager.speakToAllInterfaces(Connection.MAILBOX_MENU_TEXT);
        }
    }


    public void record(String voice, Connection connection) {}


    public void hangup(Connection connection) {}
}
