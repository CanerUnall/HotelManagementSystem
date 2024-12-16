package com.cap.MolvenoLakeResort.payload.request.user;

public class ChangePasswordRequest {
    private String newPassword;
    private String userId;
    private String email;

    // Getters and Setters


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
