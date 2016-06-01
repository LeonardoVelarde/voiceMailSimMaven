package Model.ConnectionStates;

import Controller.ConnectionController;

public class ChangePasscodeState implements ConnectionState {

    public void operate(String key, ConnectionController connectionController) {
        if (key.equals("#"))
        {
            connectionController.currentMailbox.setPasscode(connectionController.accumulatedKeys);
            connectionController.state = new MailBoxMenuState();
            connectionController.interfaceManager.speakToAllInterfaces(ConnectionController.MAILBOX_MENU_TEXT);
            connectionController.accumulatedKeys = "";
        }
        else
            connectionController.accumulatedKeys += key;
    }


    public void record(String voice, ConnectionController connectionController) {}


    public void hangup(ConnectionController connectionController) {}
}
