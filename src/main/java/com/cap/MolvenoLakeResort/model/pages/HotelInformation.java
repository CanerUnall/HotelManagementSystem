package com.cap.MolvenoLakeResort.model.pages;

import com.cap.MolvenoLakeResort.model.user.Address;
import jakarta.persistence.*;

@Entity
public class HotelInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelId;
    private String hotelName;
    @Embedded
    private Address address;
    private String phoneNumber;
    private String email;
    private String facebook;
    private String twitter;
    private String instagram;
    private String restaurant;

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public HotelInformation(Long hotelId, String hotelName, Address address, String phoneNumber, String email,
                            String facebook, String twitter, String instagram, String restaurant) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.facebook = facebook;
        this.twitter = twitter;
        this.instagram = instagram;
        this.restaurant = restaurant;
    }

    public HotelInformation() {
    }
}
