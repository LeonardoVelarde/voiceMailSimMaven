package Model.ConnectionStates;


import Controller.ConnectionController;

public class ShowContactsState implements ConnectionState{
    public void operate(String key, ConnectionController connectionController) {
        if (key.equals("1"))
        {
            connectionController.state = new ContactMenuState();
            connectionController.speakToAllInterfaces(ConnectionController.CONTACT_MENU_TEXT);
        }
    }


    public void record(String voice, ConnectionController connectionController) {}


    public void hangup(ConnectionController connectionController) {}
}
