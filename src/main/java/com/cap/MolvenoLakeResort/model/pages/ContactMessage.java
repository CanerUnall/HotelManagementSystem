package com.cap.MolvenoLakeResort.model.pages;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
@Entity
public class ContactMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactMessageId;
    private String author;
    private String email;
    private String message;
    private LocalDateTime dateTime;

    public Long getContactMessageId() {
        return contactMessageId;
    }

    public void setContactMessageId(Long contactMessageId) {
        this.contactMessageId = contactMessageId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public ContactMessage(Long contactMessageId, String author, String email, String message, LocalDateTime dateTime) {
        this.contactMessageId = contactMessageId;
        this.author = author;
        this.email = email;
        this.message = message;
        this.dateTime = dateTime;
    }

    public ContactMessage() {
    }
}
