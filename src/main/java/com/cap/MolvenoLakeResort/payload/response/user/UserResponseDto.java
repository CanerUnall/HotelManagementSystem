package com.cap.MolvenoLakeResort.payload.response.user;

import com.cap.MolvenoLakeResort.model.user.Address;

public class UserResponseDto {

    private Long userId;
    private String userName;
    private String userSurName;
    private String email;
    private Address address;
    private String password;
    private String phoneNumber;
    private boolean adult;
    private String userRoleType;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurName() {
        return userSurName;
    }

    public void setUserSurName(String userSurName) {
        this.userSurName = userSurName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserRoleType() {
        return userRoleType;
    }

    public void setUserRoleType(String userRoleType) {
        this.userRoleType = userRoleType;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public UserResponseDto(String userName, String userSurName, boolean adult) {
        this.userName = userName;
        this.userSurName = userSurName;
        this.adult = adult;
    }

    public UserResponseDto() {
    }
}
