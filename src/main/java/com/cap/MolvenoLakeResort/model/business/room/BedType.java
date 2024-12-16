package com.cap.MolvenoLakeResort.model.business.room;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class BedType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bedTypeId;
    private String bedTypeName;
    private String bedTypeNameZh;

    @ManyToMany(mappedBy = "bedTypeList", cascade = {CascadeType.MERGE})
    @JsonIgnoreProperties("bedTypeList")
    private List<Room> roomList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BedType bedType = (BedType) o;
        return Objects.equals(bedTypeId, bedType.bedTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(bedTypeId);
    }

    public Long getBedTypeId() {
        return bedTypeId;
    }

    public void setBedTypeId(Long bedTypeId) {
        this.bedTypeId = bedTypeId;
    }

    public String getBedTypeName() {
        return bedTypeName;
    }

    public String getBedTypeNameZh() {
        return bedTypeNameZh;
    }

    public void setBedTypeNameZh(String bedTypeNameZh) {
        this.bedTypeNameZh = bedTypeNameZh;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public void setBedTypeName(String bedTypeName) {
        this.bedTypeName = bedTypeName;
    }

//    public List<Room> getRoomList() {
//        return roomList;
//    }
//
//    public void setRoomList(List<Room> roomList) {
//        this.roomList = roomList;
//    }

    public BedType(Long bedTypeId, String bedTypeName) {
        this.bedTypeId = bedTypeId;
        this.bedTypeName = bedTypeName;

    }

    public BedType() {
    }
}
