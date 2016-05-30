import Controller.Connection;
import Controller.MailSystem;
import Model.*;
import View.UserInterface;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ConnectionRecordingTest {

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
    public void inLogItShouldVerifiPassShowMessageAndSetStateToMailBoxMenu() {

        when(mailSystem.findMailbox("1")).thenReturn(currentMailbox);
        when(currentMailbox.checkPasscode("2")).thenReturn(true);

        connection.receiveInput("1");
        connection.receiveInput("#");
        connection.receiveInput("2");
        connection.receiveInput("#");
        verify(phone).speak(MAILBOX_MENU_TEXT);
        assert (connection.isInMailBoxMenu());
    }

    @Test
    public void inLogItShouldVerifiPassReturnFalseAndShowErrorMessage() {
        when(mailSystem.findMailbox("1")).thenReturn(currentMailbox);
        when(currentMailbox.checkPasscode("2")).thenReturn(false);

        connection.receiveInput("1");
        connection.receiveInput("#");
        connection.receiveInput("2");
        connection.receiveInput("#");
        verify(phone).speak("Incorrect passcode. Try again!");
    }
}