package com.onlinequizwebapp.onlinequizwebapp.services;

import com.onlinequizwebapp.onlinequizwebapp.dao.implementations.ContactDAOImpl;
import com.onlinequizwebapp.onlinequizwebapp.domain.Choice;
import com.onlinequizwebapp.onlinequizwebapp.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    private final ContactDAOImpl contactDAOImpl;

    @Autowired
    public ContactService(ContactDAOImpl contactDAOImpl) {
        this.contactDAOImpl = contactDAOImpl;
    }

    public void createNewContact(Contact contact) {
        contactDAOImpl.createNewContact(contact);
    }

    public List<Contact> getAllContact() {
        return contactDAOImpl.getAllContact();
    }

    public Optional<Contact> getContactById(int id) {
        return Optional.ofNullable(contactDAOImpl.getContactById(id));
    }

    public Optional<Contact> getChoiceByEmail(String email) {
        return Optional.ofNullable(contactDAOImpl.getContactByEmail(email));
    }
}
