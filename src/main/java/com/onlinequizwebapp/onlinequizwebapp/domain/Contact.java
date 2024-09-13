package com.onlinequizwebapp.onlinequizwebapp.domain;


import java.util.Date;

public class Contact {
    private int id;
    private String email;
    private String subject;
    private String messageContent;
    private Date createdAt;

    public Contact(){}
    public Contact(int id, String email, String subject, String messageContent, Date createdAt) {
        this.id = id;
        this.email = email;
        this.subject = subject;
        this.messageContent = messageContent;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", subject='" + subject + '\'' +
                ", messageContent='" + messageContent + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
