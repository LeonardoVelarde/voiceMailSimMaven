package Model.ConnectionStates;

import Controller.ConnectionController;

public class ConnectedState implements ConnectionState {

    public void operate(String key, ConnectionController connectionController) {
        if (key.equals("#"))
        {
            connectionController.currentMailbox = connectionController.system.findMailbox(connectionController.accumulatedKeys);
            if (connectionController.currentMailbox != null)
            {
                connectionController.state = new RecordingState();
                connectionController.speakToAllInterfaces(connectionController.currentMailbox.getGreeting());
            }
            else
                connectionController.speakToAllInterfaces("Incorrect mailbox number. Try again!");
            connectionController.accumulatedKeys = "";
        }
        else if(key.equals("Contacts"))
        {
            connectionController.state = new ContactMenuState();
            connectionController.speakToAllInterfaces(ConnectionController.CONTACT_MENU_TEXT);
        }
        else
            connectionController.accumulatedKeys += key;
    }


    public void record(String voice, ConnectionController connectionController) {}


    public void hangup(ConnectionController connectionController) {}
}
