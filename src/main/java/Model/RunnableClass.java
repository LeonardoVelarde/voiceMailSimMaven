package Model;

import Controller.ConnectionController;
import View.InterfacePhoneConsole;

public class RunnableClass implements Runnable {
    public InterfacePhoneConsole ui;
    public ConnectionController connectionController;
    public RunnableClass(InterfacePhoneConsole ui, ConnectionController connectionController){
        this.ui = ui;
        this.connectionController = connectionController;
    }


    public void run() {
        ui.start(connectionController);
    }
}
