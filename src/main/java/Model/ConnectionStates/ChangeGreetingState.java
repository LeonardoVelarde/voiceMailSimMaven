package Model.ConnectionStates;

import Controller.ConnectionController;

public class ChangeGreetingState implements ConnectionState {

    public void operate(String key, ConnectionController connectionController) {
        if (key.equals("#"))
        {
            connectionController.currentMailbox.setGreeting(connectionController.currentRecording);
            connectionController.currentRecording = "";
            connectionController.state = new MailBoxMenuState();
            connectionController.interfaceManager.speakToAllInterfaces(ConnectionController.MAILBOX_MENU_TEXT);
        }
    }


    public void record(String voice, ConnectionController connectionController) {}


    public void hangup(ConnectionController connectionController) {}
}
