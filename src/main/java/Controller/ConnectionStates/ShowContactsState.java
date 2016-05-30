package Controller.ConnectionStates;


import Controller.Connection;

public class ShowContactsState implements ConnectionState{
    public void operate(String key, Connection connection) {
        if (key.equals("1"))
        {
            connection.state = new ContactMenuState();
            connection.speakToAllInterfaces(Connection.CONTACT_MENU_TEXT);
        }
    }


    public void record(String voice, Connection connection) {}


    public void hangup(Connection connection) {}
}
