import Controller.ConnectionController;
import Model.MailSystem;
import Model.*;
import View.UserInterface;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class ConnectionControllerMailboxSystemMenuTest {

	Mailbox currentMailbox;
    MailSystem mailSystem;
    UserInterface phone;
    ConnectionController connectionController;

    private static String MAILBOX_MENU_TEXT = "Enter 1 to listen to your messages\n"
                    + "Enter 2 to change your passcode\n"
                    + "Enter 3 to change your greeting";
   
    @Before
    public void setup() {
        currentMailbox = mock(Mailbox.class);
        mailSystem = mock(MailSystem.class);
        phone = mock(UserInterface.class);
        connectionController = new ConnectionController(mailSystem);
        connectionController.addNewInterface(phone);
    }

	
	@Test
    public void inMailSystemMenuItShouldChangePasscode() {
        when(mailSystem.findMailbox("1")).thenReturn(currentMailbox);
        when(currentMailbox.checkPasscode("1")).thenReturn(true);

        connectionController.receiveInput("1");
        connectionController.receiveInput("#");
        connectionController.receiveInput("1");
        connectionController.receiveInput("#");
        connectionController.receiveInput("2");
        connectionController.receiveInput("9");
        connectionController.receiveInput("#");
        verify(currentMailbox).setPasscode("9");
        assert (connectionController.isInMailBoxMenu());
        verify(phone,times(2)).speak(MAILBOX_MENU_TEXT);
    }

    @Test
    public void inMailSystemMenuShouldChangeGreeting(){
        when(mailSystem.findMailbox("1")).thenReturn(currentMailbox);
        when(currentMailbox.checkPasscode("1")).thenReturn(true);

        connectionController.receiveInput("1");
        connectionController.receiveInput("#");
        connectionController.receiveInput("1");
        connectionController.receiveInput("#");
        connectionController.receiveInput("3");
        connectionController.record("Greeting");
        connectionController.receiveInput("#");
        verify(currentMailbox).setGreeting("Greeting");
        assert(connectionController.isInMailBoxMenu());
        verify(phone,times(2)).speak(MAILBOX_MENU_TEXT);
    }

}
