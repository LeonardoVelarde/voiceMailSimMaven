package Controller;
import Model.Contact;

import java.util.ArrayList;

/*
 * Created by levelarde on 20-04-16.
 */
public class ContactManager {
    private ArrayList<Contact> contacts;
    ContactManager(){
        this.contacts = new ArrayList<Contact>();
    }

    public void addContact(Contact c){
        this.contacts.add(c);
    }

    public void addContact(String name, String number){
        this.contacts.add(new Contact(name, number));
    }
}
