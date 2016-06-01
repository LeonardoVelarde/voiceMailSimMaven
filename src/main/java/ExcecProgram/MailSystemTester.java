package ExcecProgram;

import Controller.ConnectionController;
import Model.MailSystem;
import Model.RunnableClass;
import View.InterfacePhoneConsole;
import View.InterfacePhoneGUI;

/*
   This program tests the mail system. A single phone
   communicates with the program through System.in/System.out.
*/
public class MailSystemTester
{
   public static void main(String[] args)
   {
      MailSystem system = new MailSystem(MAILBOX_COUNT);
      ConnectionController c = new ConnectionController(system);
      InterfacePhoneConsole consoleInterface = new InterfacePhoneConsole();
      InterfacePhoneGUI formInterface = new InterfacePhoneGUI(c);
      c.addNewInterface(formInterface);
      c.addNewInterface(consoleInterface);
      Runnable console = new RunnableClass(consoleInterface, c);
      Thread t = new Thread(console);
      t.start();
      formInterface.pack();
      formInterface.setVisible(true);
   }

   private static final int MAILBOX_COUNT = 20;
}
