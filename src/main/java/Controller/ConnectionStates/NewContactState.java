package Controller.ConnectionStates;


import Controller.Connection;
import Model.Contact;

public class NewContactState implements ConnectionState{
    public void operate(String key, Connection connection) {
        String[] contact = key.split(" ");
        Contact contact1 = new Contact(contact[0], contact[1], contact[2], null);
        contact1.save();
        connection.speakToAllInterfaces("Contact added.\n" + Connection.CONTACT_MENU_TEXT);
        connection.state = new ContactMenuState();
    }


    public void record(String voice, Connection connection) {}


    public void hangup(Connection connection) {}
}
