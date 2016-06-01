package Model.ConnectionStates;


import Controller.ConnectionController;
import Model.Contact;

public class DeleteContactState implements ConnectionState{
    public void operate(String key, ConnectionController connectionController) {
        if (key.equals("#"))
        {
            Contact.deleteContact(Integer.parseInt(connectionController.accumulatedKeys.split("")[connectionController.accumulatedKeys.length()-1]));
            connectionController.speakToAllInterfaces("Contact deleted, type 'Back' to go back.");
        }
        else if(key.equals("Back")){
            connectionController.speakToAllInterfaces(ConnectionController.CONTACT_MENU_TEXT);
            connectionController.state = new ContactMenuState();
        }
        else
            connectionController.accumulatedKeys += key;
    }


    public void record(String voice, ConnectionController connectionController) {}


    public void hangup(ConnectionController connectionController) {}
}
