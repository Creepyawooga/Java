package example.contactapp;

import java.util.ArrayList;
import java.util.List;

public class ContactService {
    private List<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact) {
        contacts.add(contact);
        System.out.println("Контакт добавлен: " + contact);
    }

    public void removeContact(String email) {
        contacts.removeIf(contact -> contact.getEmail().equals(email));
        System.out.println("Контакт с email " + email + " удалён.");
    }

    public void listContacts() {
        System.out.println("Список контактов:");
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }
}
