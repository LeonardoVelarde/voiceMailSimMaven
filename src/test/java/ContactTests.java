import Model.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContactTests {
    @Test
    public void itShouldCreateAnObject() {
        Contact contact = new Contact("Nombre", "Apellido" ,"Numero", null);
        assertNotNull(contact);
    }

    @Test
    public void itShouldEditContactName() {
        Contact contact = new Contact("Nombre", "Apellido" ,"Numero", null);
        String name = "Nombre2";
        contact.setFirstName(name);
        assertEquals(name+" Apellido", contact.getName());
    }

    @Test
    public void itShouldEditContactNumber() {
        Contact contact = new Contact("Nombre", "Apellido" ,"Numero", null);
        String number = "Numero2";
        contact.setNumber(number);
        assertEquals(number, contact.getNumber());
    }
}
