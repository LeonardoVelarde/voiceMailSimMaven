import Model.*;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


public class MessageTest {

	@Test
	public void itShouldReturnTheMessage() {
		Message message = new Message("Hola mundo");
		assertEquals("Hola mundo", message.getText());
	}

}
