package com.cap.MolvenoLakeResort.model.business.room;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roomTypeId;

    private String roomTypeName;
    private String roomTypeNameZh;

    @OneToMany(mappedBy = "roomType", cascade = CascadeType.MERGE)
    private List<Room> roomList = new ArrayList<>();
    public String getRoomTypeNameZh() {
        return roomTypeNameZh;
    }

    public void setRoomTypeNameZh(String roomTypeNameZh) {
        this.roomTypeNameZh = roomTypeNameZh;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }



    public RoomType(Long roomTypeId, String roomTypeName, List<Room> roomList) {
        this.roomTypeId = roomTypeId;
        this.roomTypeName = roomTypeName;
       // this.roomList = roomList;
    }

    public RoomType() {
    }
}
