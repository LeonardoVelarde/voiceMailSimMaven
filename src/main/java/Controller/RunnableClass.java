package Controller;

import View.InterfacePhoneConsole;

public class RunnableClass implements Runnable {
    public InterfacePhoneConsole ui;
    public Connection connection;
    public RunnableClass(InterfacePhoneConsole ui, Connection connection){
        this.ui = ui;
        this.connection = connection;
    }


    public void run() {
        ui.start(connection);
    }
}
