package com.cap.MolvenoLakeResort.payload.request.page;

public class ContactMessageRequestDto {

    private Long contactMessageId;
    private String author;
    private String email;
    private String message;

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

    public ContactMessageRequestDto(Long contactMessageId, String author, String email, String message) {
        this.contactMessageId = contactMessageId;
        this.author = author;
        this.email = email;
        this.message = message;
    }

    public ContactMessageRequestDto() {
    }
}
