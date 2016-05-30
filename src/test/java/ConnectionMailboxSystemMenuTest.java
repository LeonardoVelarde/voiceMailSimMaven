import Controller.Connection;
import Controller.MailSystem;
import Model.*;
import View.UserInterface;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class ConnectionMailboxSystemMenuTest {

	Mailbox currentMailbox;
    MailSystem mailSystem;
    UserInterface phone;
    Connection connection;

    private static String MAILBOX_MENU_TEXT = "Enter 1 to listen to your messages\n"
                    + "Enter 2 to change your passcode\n"
                    + "Enter 3 to change your greeting";
   
    @Before
    public void setup() {
        currentMailbox = mock(Mailbox.class);
        mailSystem = mock(MailSystem.class);
        phone = mock(UserInterface.class);
        connection = new Connection(mailSystem);
        connection.addNewInterface(phone);
    }

	
	@Test
    public void inMailSystemMenuItShouldChangePasscode() {
        when(mailSystem.findMailbox("1")).thenReturn(currentMailbox);
        when(currentMailbox.checkPasscode("1")).thenReturn(true);

        connection.receiveInput("1");
        connection.receiveInput("#");
        connection.receiveInput("1");
        connection.receiveInput("#");
        connection.receiveInput("2");
        connection.receiveInput("9");
        connection.receiveInput("#");
        verify(currentMailbox).setPasscode("9");
        assert (connection.isInMailBoxMenu());
        verify(phone,times(2)).speak(MAILBOX_MENU_TEXT);
    }

    @Test
    public void inMailSystemMenuShouldChangeGreeting(){
        when(mailSystem.findMailbox("1")).thenReturn(currentMailbox);
        when(currentMailbox.checkPasscode("1")).thenReturn(true);

        connection.receiveInput("1");
        connection.receiveInput("#");
        connection.receiveInput("1");
        connection.receiveInput("#");
        connection.receiveInput("3");
        connection.record("Greeting");
        connection.receiveInput("#");
        verify(currentMailbox).setGreeting("Greeting");
        assert(connection.isInMailBoxMenu());
        verify(phone,times(2)).speak(MAILBOX_MENU_TEXT);
    }

}
