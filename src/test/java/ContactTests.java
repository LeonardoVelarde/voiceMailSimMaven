import Model.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContactTests {
    @Test
    public void itShouldCreateAnObject() {
        Contact contact = new Contact("Nombre","Numero");
        assertNotNull(contact);
    }

    @Test
    public void itShouldEditContactName() {
        Contact contact = new Contact("Nombre","Numero");
        String name = "Nombre2";
        contact.setName(name);
        assertEquals(name, contact.getName());
    }

    @Test
    public void itShouldEditContactNumber() {
        Contact contact = new Contact("Nombre","Numero");
        String number = "Numero2";
        contact.setNumber(number);
        assertEquals(number, contact.getNumber());
    }
}
