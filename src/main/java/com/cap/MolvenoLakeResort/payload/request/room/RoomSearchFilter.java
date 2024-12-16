package com.cap.MolvenoLakeResort.payload.request.room;

import java.time.LocalDateTime;
import java.util.List;

public class RoomSearchFilter {

    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private Integer adults;
    private Integer children;
    private String roomType;
    private Boolean smoking;
    private List<String> facilities;
    private List<String> bedTypes;
    private String floor;
    private Boolean disabledFriendly;

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }

    public Integer getAdults() {
        return adults;
    }

    public void setAdults(Integer adults) {
        this.adults = adults;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Boolean getSmoking() {
        return smoking;
    }

    public void setSmoking(Boolean smoking) {
        this.smoking = smoking;
    }

    public List<String> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<String> facilities) {
        this.facilities = facilities;
    }

    public List<String> getBedTypes() {
        return bedTypes;
    }

    public void setBedTypes(List<String> bedTypes) {
        this.bedTypes = bedTypes;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public Boolean getDisabledFriendly() {
        return disabledFriendly;
    }

    public void setDisabledFriendly(Boolean disabledFriendly) {
        this.disabledFriendly = disabledFriendly;
    }

    @Override
    public String toString() {
        return "RoomSearchFilter{" +
                "checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", adults=" + adults +
                ", children=" + children +
                ", roomType='" + roomType + '\'' +
                ", smoking=" + smoking +
                ", facilities=" + facilities +
                ", bedTypes=" + bedTypes +
                ", floor='" + floor + '\'' +
                ", disabledFriendly=" + disabledFriendly +
                '}';
    }
}
