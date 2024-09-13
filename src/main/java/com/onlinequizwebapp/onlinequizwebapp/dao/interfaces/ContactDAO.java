package com.onlinequizwebapp.onlinequizwebapp.dao.interfaces;

import com.onlinequizwebapp.onlinequizwebapp.domain.Contact;

import java.util.List;

public interface ContactDAO {
    void createNewContact(Contact contact);
    List<Contact> getAllContact();
    Contact getContactById(Integer id);
    Contact getContactByEmail(String email);
}
