import Controller.ConnectionController;
import Model.MailSystem;
import Model.*;
import View.InterfacePhoneConsole;
import View.UserInterface;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ConnectionControllerMailboxMenuTest {


    Mailbox currentMailbox;
    MailSystem mailSystem;
    UserInterface phone;
    ConnectionController connectionController;

    @Before
    public void setup() {
        currentMailbox = mock(Mailbox.class);
        mailSystem = mock(MailSystem.class);
        phone = mock(InterfacePhoneConsole.class);
        connectionController = new ConnectionController(mailSystem);
        connectionController.addNewInterface(phone);
        when(mailSystem.findMailbox("1")).thenReturn(currentMailbox);
        when(currentMailbox.checkPasscode("1")).thenReturn(true);
        inMailboxLoggedIn();
    }

    @Test
    public void inMailboxMenuEnter1ForListenMessages() {
        String MESSAGE_MENU_TEXT = "Enter 1 to listen to the current message\n"
                + "Enter 2 to save the current message\n"
                + "Enter 3 to delete the current message\n"
                + "Enter 4 to return to the main menu";
        connectionController.receiveInput("1");
        assert(connectionController.isInMessageMenu());
        verify(phone).speak(MESSAGE_MENU_TEXT);
    }

    @Test
    public void inMailboxMenuEnter2ForChangingPasscode() {

        connectionController.receiveInput("2");
        assert (connectionController.isInChangePassword());
        verify(phone).speak("Enter new passcode followed by the # key");
    }

    @Test
    public void inMailboxMenuEnter3ForChangingGreeting() {

        connectionController.receiveInput("3");
        assert (connectionController.isInChangeGreeting());
        verify(phone).speak("Record your greeting, then press the # key");
    }

    private void inMailboxLoggedIn() {
        connectionController.receiveInput("1");
        connectionController.receiveInput("#");
        connectionController.receiveInput("1");
        connectionController.receiveInput("#");
    }
}