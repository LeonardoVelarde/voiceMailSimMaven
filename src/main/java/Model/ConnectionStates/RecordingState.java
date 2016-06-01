package Model.ConnectionStates;
import Controller.ConnectionController;

public class RecordingState implements ConnectionState {


    public void operate(String key, ConnectionController connectionController) {
        if (key.equals("#"))
        {
            if (connectionController.currentMailbox.checkPasscode(connectionController.accumulatedKeys))
            {
                connectionController.state = new MailBoxMenuState();
                connectionController.interfaceManager.speakToAllInterfaces(connectionController.MAILBOX_MENU_TEXT);
            }
            else
                connectionController.interfaceManager.speakToAllInterfaces("Incorrect passcode. Try again!");
            connectionController.accumulatedKeys = "";
        }
        else
            connectionController.accumulatedKeys += key;
    }


    public void record(String voice, ConnectionController connectionController) {}


    public void hangup(ConnectionController connectionController) {}
}
