package com.onlinequizwebapp.onlinequizwebapp.dao.implementations;

import com.onlinequizwebapp.onlinequizwebapp.dao.rowMapper.ContactRowMapper;
import com.onlinequizwebapp.onlinequizwebapp.dao.interfaces.ContactDAO;
import com.onlinequizwebapp.onlinequizwebapp.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactDAOImpl implements ContactDAO {
    JdbcTemplate jdbcTemplate;
    ContactRowMapper rowMapper;

    @Autowired
    public ContactDAOImpl(JdbcTemplate jdbcTemplate, ContactRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    // 1. CREATE a contact
    @Override
    public void createNewContact(Contact contact){
        String query = "INSERT INTO contacts (email, emailSubject, message, createdAt) values (?, ?, ?,now())";
        jdbcTemplate.update(query, contact.getEmail(), contact.getSubject(), contact.getMessageContent());
    }

    // 2. READ all contacts
    @Override
    public List<Contact> getAllContact(){
        String query = "SELECT * FROM contacts";

        List<Contact> contacts = jdbcTemplate.query(query, rowMapper);

        return contacts;
    }

    // 3. READ a contact by id
    @Override
    public Contact getContactById(Integer id){
        String query = "SELECT * FROM Contacts WHERE contactId=?";

        List<Contact> contacts = jdbcTemplate.query(query, rowMapper, id);

        return contacts.size() == 0 ? null : contacts.get(0);
    }

    // 4. READ a contact by email
    @Override
    public Contact getContactByEmail(String email){
        String query = "SELECT * FROM contacts WHERE email=?";

        List<Contact> contacts = jdbcTemplate.query(query, rowMapper, email);

        return contacts.size() == 0 ? null : contacts.get(0);
    }
}
