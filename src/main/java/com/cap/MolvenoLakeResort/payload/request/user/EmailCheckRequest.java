package com.cap.MolvenoLakeResort.payload.request.user;

public class EmailCheckRequest {
    private String email;


    public EmailCheckRequest() {}
    public EmailCheckRequest(String email){
        this.email=email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
