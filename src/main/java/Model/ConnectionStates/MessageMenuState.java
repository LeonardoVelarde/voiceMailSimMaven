package Model.ConnectionStates;

import Controller.ConnectionController;
import Model.Message;

public class MessageMenuState implements ConnectionState {

    public void operate(String key, ConnectionController connectionController) {
        if (key.equals("1"))
        {
            String output = "";
            Message m = connectionController.currentMailbox.getCurrentMessage();
            if (m == null) output += "No messages." + "\n";
            else output += m.getText() + "\n";
            output += ConnectionController.MESSAGE_MENU_TEXT;
            connectionController.interfaceManager.speakToAllInterfaces(output);
        }
        else if (key.equals("2"))
        {
            connectionController.currentMailbox.saveCurrentMessage();
            connectionController.interfaceManager.speakToAllInterfaces(ConnectionController.MESSAGE_MENU_TEXT);
        }
        else if (key.equals("3"))
        {
            connectionController.currentMailbox.removeCurrentMessage();
            connectionController.interfaceManager.speakToAllInterfaces(ConnectionController.MESSAGE_MENU_TEXT);
        }
        else if (key.equals("4"))
        {
            connectionController.state = new MailBoxMenuState();
            connectionController.interfaceManager.speakToAllInterfaces(ConnectionController.MAILBOX_MENU_TEXT);
        }
    }


    public void record(String voice, ConnectionController connectionController) {}


    public void hangup(ConnectionController connectionController) {}
}
