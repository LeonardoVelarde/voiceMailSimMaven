import Controller.ConnectionController;
import Model.MailSystem;
import Model.*;
import View.UserInterface;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ConnectionControllerNewTest {

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
    }

    @Test
    public void newConnectionShouldShowInitialPromotAndSetStateToConnected() {
        verify(phone).speak("Enter mailbox number followed by # (or enter Contacts to see contact menu)");
        assert (connectionController.isConnected());
    }

    @Test
    public void asConnectedDial1shouldGetMailBoxSpeakGreetingAndSetStateToRecording() {
        when(mailSystem.findMailbox("1")).thenReturn(currentMailbox);
        connectionController.receiveInput("1");
        connectionController.receiveInput("#");
        verify(phone).speak(currentMailbox.getGreeting());
        assert (connectionController.isRecording());
    }

    @Test
    public void asConnectedDial10shouldGetNullSpeakErrorMsjAndSetStateToRecording() {
        when(mailSystem.findMailbox("10")).thenReturn(null);
        connectionController.receiveInput("1");
        connectionController.receiveInput("#");
        verify(phone).speak("Incorrect mailbox number. Try again!");
    }

    @Test
    public void afterRecordingHangoutShouldSaveAMessageAndResetConnection(){
        String msgText = "This is a new message.";
        when(mailSystem.findMailbox("1")).thenReturn(currentMailbox);
        when(currentMailbox.checkPasscode("1")).thenReturn(true);
        when(currentMailbox.getCurrentMessage()).thenReturn(new Message(msgText));

        connectionController.receiveInput("1");
        connectionController.receiveInput("#");
        connectionController.receiveInput(msgText);
        connectionController.hangup();
        connectionController.receiveInput("1");
        connectionController.receiveInput("#");
        connectionController.receiveInput("1");
        connectionController.receiveInput("#");
        connectionController.receiveInput("1");
        connectionController.receiveInput("1");
        verify(phone).speak(msgText+"\n"+MESSAGE_MENU_TEXT);
    }
}
