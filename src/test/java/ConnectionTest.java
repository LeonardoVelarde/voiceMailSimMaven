import Controller.Connection;
import Controller.MailSystem;
import Model.*;
import View.InterfacePhoneConsole;
import View.UserInterface;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ConnectionTest {

	@Test
	public void newConnectionShouldBeConnected() {
		MailSystem system = mock(MailSystem.class);
		UserInterface interfaceConsole = mock(InterfacePhoneConsole.class);
	    Connection conn = new Connection(system);
		conn.addNewInterface(interfaceConsole);

	    assertTrue(conn.isConnected());
	}

	@Test
	public void whenDialInAConnectedStateItShouldChangeToRecording() {
		MailSystem system = mock(MailSystem.class);
		UserInterface interfaceConsole = mock(InterfacePhoneConsole.class);
		Connection conn = new Connection(system);
		Mailbox mailbox = mock(Mailbox.class);
		conn.addNewInterface(interfaceConsole);

		when(system.findMailbox("1")).thenReturn(mailbox);
		when(mailbox.getGreeting()).thenReturn("Hola mailbox");

	    conn.receiveInput("1");
	    conn.receiveInput("#");

		verify(interfaceConsole).speak("Hola mailbox");
	    assertTrue(conn.isRecording());
	}

	@Test
	public void whenDialInAConnectedStateAndNoMailboxFoundItShouldShowAnErrorMessage() {
		MailSystem system = mock(MailSystem.class);
		UserInterface interfaceConsole = mock(InterfacePhoneConsole.class);
		Connection conn = new Connection(system);
		conn.addNewInterface(interfaceConsole);

		when(system.findMailbox("10")).thenReturn(null);

		conn.receiveInput("10");
		conn.receiveInput("#");

		verify(interfaceConsole).speak("Incorrect mailbox number. Try again!");
	}

	@Test
	public void test1() {
		MailSystem system = mock(MailSystem.class);
		Connection conn = new Connection(system);
		Mailbox currentMailbox = mock(Mailbox.class);
		String mailboxText = "Enter 1 to listen to your messages\n"
				+ "Enter 2 to change your passcode\n"
				+ "Enter 3 to change your greeting";
		UserInterface interfaceConsole = mock(InterfacePhoneConsole.class);
		conn.addNewInterface(interfaceConsole);

		when(system.findMailbox("1")).thenReturn(currentMailbox);
		when(currentMailbox.checkPasscode("1")).thenReturn(true);

	    conn.receiveInput("1");
	    conn.receiveInput("#");
	    conn.receiveInput("1");
	    conn.receiveInput("#");

	    assertTrue(conn.isInMailBoxMenu());
		verify(interfaceConsole).speak(mailboxText);

	}

	@Test
	public void test2() {
		MailSystem system = mock(MailSystem.class);
		Connection conn = new Connection(system);
		Mailbox currentMailbox = mock(Mailbox.class);
		String mailboxText = "Incorrect passcode. Try again!";
		UserInterface interfaceConsole = mock(InterfacePhoneConsole.class);
		conn.addNewInterface(interfaceConsole);

		when(system.findMailbox("1")).thenReturn(currentMailbox);
		when(currentMailbox.checkPasscode("2")).thenReturn(false);

	    conn.receiveInput("1");
	    conn.receiveInput("#");
	    conn.receiveInput("2");
	    conn.receiveInput("#");

	    assertFalse(conn.isInMailBoxMenu());
		verify(interfaceConsole).speak(mailboxText);
	}

	@Test
	public void getIntoChangePasscodeOption(){
		MailSystem system = mock(MailSystem.class);
		Connection conn = new Connection(system);
		Mailbox currentMailbox = mock(Mailbox.class);
		UserInterface interfaceConsole = mock(InterfacePhoneConsole.class);
		conn.addNewInterface(interfaceConsole);

		when(system.findMailbox("1")).thenReturn(currentMailbox);
		when(currentMailbox.checkPasscode("1")).thenReturn(true);

	    conn.receiveInput("1");
	    conn.receiveInput("#");
	    conn.receiveInput("1");
	    conn.receiveInput("#");
	    conn.receiveInput("2");
		verify(interfaceConsole).speak("Enter new passcode followed by the # key");
	}
	
}
