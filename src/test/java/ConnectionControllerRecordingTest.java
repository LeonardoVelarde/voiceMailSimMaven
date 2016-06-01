import Controller.ConnectionController;
import Model.MailSystem;
import Model.*;
import View.UserInterface;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ConnectionControllerRecordingTest {

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
    public void inLogItShouldVerifiPassShowMessageAndSetStateToMailBoxMenu() {

        when(mailSystem.findMailbox("1")).thenReturn(currentMailbox);
        when(currentMailbox.checkPasscode("2")).thenReturn(true);

        connectionController.receiveInput("1");
        connectionController.receiveInput("#");
        connectionController.receiveInput("2");
        connectionController.receiveInput("#");
        verify(phone).speak(MAILBOX_MENU_TEXT);
        assert (connectionController.isInMailBoxMenu());
    }

    @Test
    public void inLogItShouldVerifiPassReturnFalseAndShowErrorMessage() {
        when(mailSystem.findMailbox("1")).thenReturn(currentMailbox);
        when(currentMailbox.checkPasscode("2")).thenReturn(false);

        connectionController.receiveInput("1");
        connectionController.receiveInput("#");
        connectionController.receiveInput("2");
        connectionController.receiveInput("#");
        verify(phone).speak("Incorrect passcode. Try again!");
    }
}