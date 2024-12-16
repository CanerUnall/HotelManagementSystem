package com.cap.MolvenoLakeResort.payload.request.room;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class RoomRequestDto {
    private long roomId;
    private String roomNumber;
    private String roomType;
    private int countOfAdult;
    private int countOfChild;
    private String floor;
    private double price;
    private List<String> facilitiesList;
    private List<String> roomBedTypeList;
    private boolean isCleaned;
    private boolean smoking;
    private boolean disabled;
    private List<MultipartFile> photos;
    private String description;
    private String descriptionZh;

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


    public int getCountOfAdult() {
        return countOfAdult;
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

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
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

    public List<String> getRoomBedTypeList() {
        return roomBedTypeList;
    }

    public void setRoomBedTypeList(List<String> roomBedTypeList) {
        this.roomBedTypeList = roomBedTypeList;
    }

    public boolean isCleaned() {
        return isCleaned;
    }

    public void setCleaned(boolean cleaned) {
        isCleaned = cleaned;
    }

    public boolean isSmoking() {
        return smoking;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }


    public List<MultipartFile> getPhotos() {
        return photos;
    }

    public void setPhotos(List<MultipartFile> photos) {
        this.photos = photos;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
