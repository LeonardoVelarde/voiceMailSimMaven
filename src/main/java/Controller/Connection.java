package Controller;

import Controller.ConnectionStates.*;
import Model.Mailbox;
import Model.Message;
import View.UserInterface;

/*
   Connects a phone to the mail system. The purpose of this
   class is to keep track of the state3 of a connection, since
   the phone itself is just a source of individual key presses.
*/
public class Connection
{
   public MailSystem system;
   public Mailbox currentMailbox;
   public String currentRecording;
   public String accumulatedKeys;
   public InterfaceManager interfaceManager;
   public ConnectionState state;
   public ContactManager contactManager;

   public static final String INITIAL_PROMPT =
           "Enter mailbox number followed by # (or enter Contacts to see contact menu)";
   public static final String MAILBOX_MENU_TEXT =
           "Enter 1 to listen to your messages\n"
                   + "Enter 2 to change your passcode\n"
                   + "Enter 3 to change your greeting";
   public static final String MESSAGE_MENU_TEXT =
           "Enter 1 to listen to the current message\n"
                   + "Enter 2 to save the current message\n"
                   + "Enter 3 to delete the current message\n"
                   + "Enter 4 to return to the main menu";

   public static final String CONTACT_MENU_TEXT =
           "Enter 1 to see your contacts\n"
                   + "Enter 2 to add a contact\n"
                   + "Enter 3 to delete a contact\n"
                   + "Enter 4 to go back";


   public Connection(MailSystem s)
   {
      this.system = s;
      this.interfaceManager = new InterfaceManager();
      this.contactManager = new ContactManager();
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
      else if (input.length() == 1  && "1234567890#".contains(input) || input.equals("Contacts"))
         dial(input);
      else if(state instanceof NewContactState || (state instanceof DeleteContactState) && input.equals("Back"))
         dial(input);
      else
         record(input);
   }

   public void dial(String key)
   {
      state.operate(key, this);
   }

   public void record(String voice)
   {
      if (isRecording() || isInChangeGreeting())
         currentRecording += voice;
   }

   public void hangup()
   {
      if (isRecording())
         currentMailbox.addMessage(new Message(currentRecording));
      resetConnection();
   }

   private void resetConnection()
   {
      currentRecording = "";
      accumulatedKeys = "";
      state = new ConnectedState();
      this.interfaceManager.speakToAllInterfaces(INITIAL_PROMPT);
   }

   public void speakToAllInterfaces(String message){
      this.interfaceManager.speakToAllInterfaces(message);
   }


   public boolean isConnected() {
      return state instanceof ConnectedState;
   }

   public boolean isRecording() {
      return state instanceof RecordingState;
   }

   public boolean isInMailBoxMenu() {
      return state instanceof MailBoxMenuState;
   }

   public boolean isInMessageMenu() {
      return state instanceof MessageMenuState;
   }

   public boolean isInChangePassword() {
      return state instanceof ChangePasscodeState;
   }

   public boolean isInChangeGreeting() {
      return state instanceof ChangeGreetingState;
   }

}











