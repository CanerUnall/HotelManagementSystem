package com.cap.MolvenoLakeResort.payload.response.page;

public class HotelAmenitiesResponse {
    private Long hotelAmenitiesId;
    private String hotelAmenitiesName;
    private String hotelAmenitiesContent;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
