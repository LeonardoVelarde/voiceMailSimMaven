package Model.ConnectionStates;


import Controller.ConnectionController;
import Database.ContactMapper;
import Model.Contact;

public class DeleteContactState implements ConnectionState{
    public void operate(String key, ConnectionController connectionController) {
        if(key.equals("Back")){
            connectionController.speakToAllInterfaces(ConnectionController.CONTACT_MENU_TEXT);
            connectionController.state = new ContactMenuState();
        }
        else {
            if(key.matches("[0-9]+")){
                ContactMapper.deleteFromDB(key);
            }
            connectionController.speakToAllInterfaces("Contact deleted, type 'Back' to go back.");
        }
    }


    public void record(String voice, ConnectionController connectionController) {}


    public void hangup(ConnectionController connectionController) {}
}
