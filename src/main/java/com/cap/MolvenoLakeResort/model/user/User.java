package com.cap.MolvenoLakeResort.model.user;

import com.cap.MolvenoLakeResort.model.business.reservation.Reservation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String userSurName;
    private String password;
    @Column(unique = true)
    private String email;

    @Embedded
    private Address address;
    private String phoneNumber;

    @ManyToOne
    private UserRoleType userRoleType;

    @JsonIgnoreProperties("guestList")
    @ManyToMany(mappedBy = "guestList", cascade = CascadeType.ALL)
    private List<Reservation> reservationList = new ArrayList<>();

    private boolean adult;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode( ) {
        return Objects.hashCode(userId);
    }

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

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getUserSurName( ) {
        return userSurName;
    }

    public void setUserSurName(String userSurName ) {
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

    public UserRoleType getUserRoleType() {
        return userRoleType;
    }

    public void setUserRoleType(UserRoleType userRoleType) {
        this.userRoleType = userRoleType;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public User(Long userId, String userName, String userSurName,
                String password, String email, Address address,
                String phoneNumber, UserRoleType userRoleType,
                List<Reservation> reservationList, boolean adult) {
        this.userId = userId;
        this.userName = userName;
        this.userSurName = userSurName;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.userRoleType = userRoleType;
        this.reservationList = reservationList;
        this.adult = adult;
    }

    public User( ) {
    }
}
