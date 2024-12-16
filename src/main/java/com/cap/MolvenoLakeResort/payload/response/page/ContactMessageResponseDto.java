package com.cap.MolvenoLakeResort.payload.response.page;

import java.time.LocalDateTime;

public class ContactMessageResponseDto {
    private Long contactMessageId;
    private String author;
    private String email;
    private String message;
    private LocalDateTime creationDateTime;

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

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public ContactMessageResponseDto(Long contactMessageId, String author, String email, String message, LocalDateTime dateTime) {
        this.contactMessageId = contactMessageId;
        this.author = author;
        this.email = email;
        this.message = message;
        this.creationDateTime = dateTime;
    }

    public ContactMessageResponseDto() {
    }
}
