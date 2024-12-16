package com.cap.MolvenoLakeResort.payload.request.user;

import com.cap.MolvenoLakeResort.model.user.Address;

public class UserRequestDto {

    private Long userId;
    private String userName;
    private String userSurName;
    private String password;
    private String email;
    private Address address;
    private boolean adult;


    private String phoneNumber;
    private String  userRoleType;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    @Override
    public String toString() {
        return "UserRequestDto{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userSurName='" + userSurName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", adult=" + adult +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userRoleType='" + userRoleType + '\'' +
                '}';
    }
}
