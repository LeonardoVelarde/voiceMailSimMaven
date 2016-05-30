package Controller;

import Controller.ConnectionStates.ConnectedState;
import Controller.ConnectionStates.ConnectionState;
import Model.Mailbox;
import Model.Message;
import View.UserInterface;

/*
   Connects a phone to the mail system. The purpose of this
   class is to keep track of the state of a connection, since
   the phone itself is just a source of individual key presses.
*/
public class Connection
{
   public MailSystem system;
   public Mailbox currentMailbox;
   public String currentRecording;
   public String accumulatedKeys;
   public InterfaceManager interfaceManager;
   public int state;
   public ConnectionState state2;

   public static final int CONNECTED = 1;
   public static final int RECORDING = 2;
   public static final int MAILBOX_MENU = 3;
   public static final int MESSAGE_MENU = 4;
   public static final int CHANGE_PASSCODE = 5;
   public static final int CHANGE_GREETING = 6;

   public static final String INITIAL_PROMPT =
           "Enter mailbox number followed by #";
   public static final String MAILBOX_MENU_TEXT =
           "Enter 1 to listen to your messages\n"
                   + "Enter 2 to change your passcode\n"
                   + "Enter 3 to change your greeting";
   public static final String MESSAGE_MENU_TEXT =
           "Enter 1 to listen to the current message\n"
                   + "Enter 2 to save the current message\n"
                   + "Enter 3 to delete the current message\n"
                   + "Enter 4 to return to the main menu";


   public Connection(MailSystem s)
   {
      this.system = s;
      this.interfaceManager = new InterfaceManager();
   }

   public void addNewInterface(UserInterface userInterface){
      this.interfaceManager.addInterface(userInterface);
      resetConnection();
   }


   public void receiveInput(String input){
      if (input == null) return;
      if (input.equalsIgnoreCase("H"))
         hangup();
      else if (input.equalsIgnoreCase("Q"))
         System.exit(0);
      else if (input.length() == 1  && "1234567890#".contains(input))
         dial(input);
      else
         record(input);
   }

   public void dial(String key)
   {
      state2.operate(key, this);
   }

   public void record(String voice)
   {
      if (state == RECORDING || state == CHANGE_GREETING)
         currentRecording += voice;
   }

   public void hangup()
   {
      if (state == RECORDING)
         currentMailbox.addMessage(new Message(currentRecording));
      resetConnection();
   }

   private void resetConnection()
   {
      currentRecording = "";
      accumulatedKeys = "";
      state = CONNECTED;
      state2 = new ConnectedState();
      this.interfaceManager.speakToAllInterfaces(INITIAL_PROMPT);
   }

   public void speakToAllInterfaces(String message){
      this.interfaceManager.speakToAllInterfaces(message);
   }


public boolean isConnected() {
    return state == CONNECTED;
 }

 public boolean isRecording() {
    return state == RECORDING;
 }

 public boolean isInMailBoxMenu() {
    return state == MAILBOX_MENU;
 }

 public boolean isInMessageMenu() {
    return state == MESSAGE_MENU;
 }

 public boolean isInChangePassword() {
    return state == CHANGE_PASSCODE;
 }

 public boolean isInChangeGreeting() {
    return state == CHANGE_GREETING;
 }

}











