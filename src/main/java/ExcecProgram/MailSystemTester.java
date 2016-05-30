package ExcecProgram;

import Controller.Connection;
import Controller.MailSystem;
import Controller.RunnableClass;
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
      Connection c = new Connection(system);
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
