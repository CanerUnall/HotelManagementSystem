package com.cap.MolvenoLakeResort.model.business.room;

import com.cap.MolvenoLakeResort.model.media.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Room {
    //sadece bire bir iliski varsa one to one
    //eger bir objenin birden fazla iliskisi varsa one to many


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    private String roomNumber;

    @ManyToOne
    private RoomType roomType;

    private double price;

    //    @JsonIgnoreProperties("roomList")
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "room_facilities",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "facility_id"))
    private List<Facilities> facilitiesList = new ArrayList<>();

    private boolean smoking;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "room_bedType",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "bedType_id"))
    private List<BedType> bedTypeList = new ArrayList<>();

    private String thFloor;
    private String description;
    private String descriptionZh;
    private int countOfAdult;
    private boolean disabled;
    private boolean cleaned = true;
    private int countOfChild;

    @JsonIgnoreProperties("roomList")
    @ManyToMany
    @JoinTable(name = "room_images",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private List<Image> imageList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(roomId, room.roomId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(roomId);
    }


    public String getDescriptionZh() {
        return descriptionZh;
    }

    public void setDescriptionZh(String descriptionZh) {
        this.descriptionZh = descriptionZh;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }



    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Facilities> getFacilitiesList() {
        return facilitiesList;
    }

    public void setFacilitiesList(List<Facilities> facilitiesList) {
        this.facilitiesList = facilitiesList;
    }

    public boolean isSmoking() {
        return smoking;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }

    public List<BedType> getBedTypeList() {
        return bedTypeList;
    }

    public void setBedTypeList(List<BedType> bedTypeList) {
        this.bedTypeList = bedTypeList;
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

    public void setCountOfAdult(int countOfAdult) {
        this.countOfAdult = countOfAdult;
    }

    public int getCountOfChild() {
        return countOfChild;
    }

    public void setCountOfChild(int countOfChild) {
        this.countOfChild = countOfChild;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }


    public boolean isCleaned() {
        return cleaned;
    }

    public void setCleaned(boolean cleaned) {
        this.cleaned = cleaned;
    }

    public Room(Long roomId, List<Image> imageList, int countOfChild, boolean cleaned, boolean disabled, int countOfAdult, String descriptionZh, String description, String thFloor, List<BedType> bedTypeList, boolean smoking, List<Facilities> facilitiesList, double price, RoomType roomType, String roomNumber) {
        this.roomId = roomId;
        this.imageList = imageList;
        this.countOfChild = countOfChild;
        this.cleaned = cleaned;
        this.disabled = disabled;
        this.countOfAdult = countOfAdult;
        this.descriptionZh = descriptionZh;
        this.description = description;
        this.thFloor = thFloor;
        this.bedTypeList = bedTypeList;
        this.smoking = smoking;
        this.facilitiesList = facilitiesList;
        this.price = price;
        this.roomType = roomType;
        this.roomNumber = roomNumber;
    }

    public Room() {
    }
}
