package com.onlinequizwebapp.onlinequizwebapp.dao.rowMapper;

import com.onlinequizwebapp.onlinequizwebapp.domain.Contact;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ContactRowMapper implements RowMapper<Contact> {
    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contact contact = new Contact();
        contact.setId(rs.getInt("contactId"));
        contact.setEmail(rs.getString("email"));
        contact.setSubject(rs.getString("emailSubject"));
        contact.setMessageContent(rs.getString("message"));
        contact.setCreatedAt(rs.getTimestamp("createdAt"));
        return contact;
    }
}
