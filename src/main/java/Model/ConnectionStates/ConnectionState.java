package Model.ConnectionStates;
import Controller.ConnectionController;

public interface ConnectionState {
    void operate(String key, ConnectionController c);
    void record(String voice, ConnectionController connectionController);
    void hangup(ConnectionController connectionController);
}
