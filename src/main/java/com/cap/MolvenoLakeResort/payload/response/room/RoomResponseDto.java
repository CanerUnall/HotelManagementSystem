package com.cap.MolvenoLakeResort.payload.response.room;

import java.util.List;

public class RoomResponseDto {

    private long roomId;
    private String roomNumber;
    private boolean disabled;
    private String roomType;
    private boolean cleaned;
    private double price;
    private List<String> facilitiesList;
    private boolean smoking;
    private String description;
    private String descriptionZh;

    private List<String> roomBedType;

    private String thFloor;

    private int countOfAdult;

    private int countOfChild;

    private List<String> imageUrlList;

    public String getDescriptionZh() {
        return descriptionZh;
    }

    public void setDescriptionZh(String descriptionZh) {
        this.descriptionZh = descriptionZh;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public boolean isCleaned() {
        return cleaned;
    }

    public void setCleaned(boolean cleaned) {
        this.cleaned = cleaned;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getFacilitiesList() {
        return facilitiesList;
    }

    public void setFacilitiesList(List<String> facilitiesList) {
        this.facilitiesList = facilitiesList;
    }

    public boolean isSmoking() {
        return smoking;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThFloor() {
        return thFloor;
    }

    public void setThFloor(String thFloor) {
        this.thFloor = thFloor;
    }

    public int getCountOfAdult() {
        return countOfAdult;
    }

    public List<String> getRoomBedType() {
        return roomBedType;
    }

    public void setRoomBedType(List<String> roomBedType) {
        this.roomBedType = roomBedType;
    }

    public void setCountOfAdult(int countOfAdult) {
        this.countOfAdult = countOfAdult;
    }

    public int getCountOfChild() {
        return countOfChild;
    }

    public void setCountOfChild(int countOfChild) {
        this.countOfChild = countOfChild;
    }

    public List<String> getImageUrlList() {
        return imageUrlList;
    }

    public void setImageUrlList(List<String> imageUrlList) {
        this.imageUrlList = imageUrlList;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
