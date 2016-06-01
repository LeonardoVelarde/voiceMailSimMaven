package Model.ConnectionStates;

import Controller.ConnectionController;

public class MailBoxMenuState implements ConnectionState {


    public void operate(String key, ConnectionController connectionController) {
        if (key.equals("1"))
        {
            connectionController.state = new MessageMenuState();
            connectionController.speakToAllInterfaces(ConnectionController.MESSAGE_MENU_TEXT);
        }
        else if (key.equals("2"))
        {
            connectionController.state = new ChangePasscodeState();
            connectionController.speakToAllInterfaces("Enter new passcode followed by the # key");
        }
        else if (key.equals("3"))
        {
            connectionController.state = new ChangeGreetingState();
            connectionController.speakToAllInterfaces("Record your greeting, then press the # key");
        }
    }


    public void record(String voice, ConnectionController connectionController) {}


    public void hangup(ConnectionController connectionController) {}
}
