import Controller.ConnectionController;
import Model.MailSystem;
import Model.*;
import View.UserInterface;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class ConnectionControllerMessageMenuTest {
    Mailbox currentMailbox;
    MailSystem mailSystem;
    UserInterface phone;
    ConnectionController connectionController;

    private static String MAILBOX_MENU_TEXT = "Enter 1 to listen to your messages\n"
            + "Enter 2 to change your passcode\n"
            + "Enter 3 to change your greeting";
    private static String MESSAGE_MENU_TEXT = "Enter 1 to listen to the current message\n"
            + "Enter 2 to save the current message\n"
            + "Enter 3 to delete the current message\n"
            + "Enter 4 to return to the main menu";

    @Before
    public void setup() {
        currentMailbox = mock(Mailbox.class);
        mailSystem = mock(MailSystem.class);
        phone = mock(UserInterface.class);
        connectionController = new ConnectionController(mailSystem);
        connectionController.addNewInterface(phone);

        when(mailSystem.findMailbox("1")).thenReturn(currentMailbox);
        when(currentMailbox.checkPasscode("1")).thenReturn(true);
        dialToMailboxMenu();

    }

    @Test
    public void inMessageMenuListenMessageNoMessagesItShouldShowError(){
        when(currentMailbox.getCurrentMessage()).thenReturn(null);
        connectionController.receiveInput("1");
        verify(phone).speak("No messages.\n"+MESSAGE_MENU_TEXT);
    }


    @Test
    public void inMessageMenuListenCurrentMessageShouldShowIT(){
        Message message = new Message("This is a message.");
        when(currentMailbox.getCurrentMessage()).thenReturn(message);
        connectionController.receiveInput("1");
        assertEquals("This is a message.",message.getText());
        verify(phone).speak("This is a message.\n"+MESSAGE_MENU_TEXT);
    }

    @Test
    public void inMessageMenuSaveCurrentMessage(){
        connectionController.receiveInput("2");
        verify(currentMailbox).saveCurrentMessage();
        verify(phone,times(2)).speak(MESSAGE_MENU_TEXT);
    }

    @Test
    public void inMessageMenuRemoveCurrentMessage() {
        connectionController.receiveInput("3");
        verify(currentMailbox).removeCurrentMessage();
        verify(phone,times(2)).speak(MESSAGE_MENU_TEXT);
    }

    @Test
    public void inMessageMenuReturnToMailboxMenu(){
        connectionController.receiveInput("4");
        assert (connectionController.isInMailBoxMenu());
        verify(phone,times(2)).speak(MAILBOX_MENU_TEXT);
    }

    private void dialToMailboxMenu() {
        connectionController.receiveInput("1");
        connectionController.receiveInput("#");
        connectionController.receiveInput("1");
        connectionController.receiveInput("#");
        connectionController.receiveInput("1");
    }

}
