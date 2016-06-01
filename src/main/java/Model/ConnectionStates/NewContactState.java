package Model.ConnectionStates;


import Controller.ConnectionController;
import Model.Contact;

public class NewContactState implements ConnectionState{
    public void operate(String key, ConnectionController connectionController) {
        String[] contact = key.split(" ");
        Contact contact1 = new Contact(contact[0], contact[1], contact[2], null);
        contact1.save();
        connectionController.speakToAllInterfaces("Contact added.\n" + ConnectionController.CONTACT_MENU_TEXT);
        connectionController.state = new ContactMenuState();
    }


    public void record(String voice, ConnectionController connectionController) {}


    public void hangup(ConnectionController connectionController) {}
}
