package com.cap.MolvenoLakeResort.model.business.room;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Facilities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facilitiesId;

    private String facilitiesName;
    private String facilitiesNameZh;

    @JsonIgnoreProperties("facilitiesList")
    @ManyToMany(mappedBy = "facilitiesList", cascade = {CascadeType.MERGE})
    private List<Room> roomList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Facilities that = (Facilities) o;
        return Objects.equals(facilitiesId, that.facilitiesId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(facilitiesId);
    }

    public Long getFacilitiesId() {
        return facilitiesId;
    }

    public void setFacilitiesId(Long facilitiesId) {
        this.facilitiesId = facilitiesId;
    }

    public String getFacilitiesName() {
        return facilitiesName;
    }

    public void setFacilitiesName(String facilitiesName) {
        this.facilitiesName = facilitiesName;
    }

//    public List<Room> getRoomList() {
//        return roomList;
//    }
//
//    public void setRoomList(List<Room> roomList) {
//        this.roomList = roomList;
//    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public String getFacilitiesNameZh() {
        return facilitiesNameZh;
    }

    public void setFacilitiesNameZh(String facilitiesNameZh) {
        this.facilitiesNameZh = facilitiesNameZh;
    }

    public Facilities(Long facilitiesId, String facilitiesName) {
        this.facilitiesId = facilitiesId;
        this.facilitiesName = facilitiesName;

    }

    public Facilities() {
    }
}
