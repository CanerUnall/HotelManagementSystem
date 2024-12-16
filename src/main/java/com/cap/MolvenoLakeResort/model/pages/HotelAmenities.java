package com.cap.MolvenoLakeResort.model.pages;

import jakarta.persistence.*;

@Entity
public class HotelAmenities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelAmenitiesId;
    private String hotelAmenitiesName;
    private String hotelAmenitiesContent;
    private String hotelAmenitiesNameZh;
    private String hotelAmenitiesContentZh;
    private String icon;

    public Long getHotelAmenitiesId() {
        return hotelAmenitiesId;
    }

    public void setHotelAmenitiesId(Long hotelAmenitiesId) {
        this.hotelAmenitiesId = hotelAmenitiesId;
    }

    public String getHotelAmenitiesName() {
        return hotelAmenitiesName;
    }

    public void setHotelAmenitiesName(String hotelAmenitiesName) {
        this.hotelAmenitiesName = hotelAmenitiesName;
    }

    public String getHotelAmenitiesContent() {
        return hotelAmenitiesContent;
    }

    public void setHotelAmenitiesContent(String hotelAmenitiesContent) {
        this.hotelAmenitiesContent = hotelAmenitiesContent;
    }

    public String getHotelAmenitiesNameZh() {
        return hotelAmenitiesNameZh;
    }

    public void setHotelAmenitiesNameZh(String hotelAmenitiesNameZh) {
        this.hotelAmenitiesNameZh = hotelAmenitiesNameZh;
    }

    public String getHotelAmenitiesContentZh() {
        return hotelAmenitiesContentZh;
    }

    public void setHotelAmenitiesContentZh(String hotelAmenitiesContentZh) {
        this.hotelAmenitiesContentZh = hotelAmenitiesContentZh;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public HotelAmenities(Long hotelAmenitiesId, String hotelAmenitiesName, String hotelAmenitiesContent, String hotelAmenitiesNameZh,
                          String hotelAmenitiesContentZh, String icon) {
        this.hotelAmenitiesId = hotelAmenitiesId;
        this.hotelAmenitiesName = hotelAmenitiesName;
        this.hotelAmenitiesContent = hotelAmenitiesContent;
        this.hotelAmenitiesNameZh = hotelAmenitiesNameZh;
        this.hotelAmenitiesContentZh = hotelAmenitiesContentZh;
        this.icon = icon;
    }

    public HotelAmenities() {
    }
}
