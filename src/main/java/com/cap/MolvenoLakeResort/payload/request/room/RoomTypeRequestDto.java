package com.cap.MolvenoLakeResort.payload.request.room;

public class RoomTypeRequestDto {
    private Long roomTypeId;
    private String roomTypeName;
    private String roomTypeNameZh;

    public String getRoomTypeNameZh() {
        return roomTypeNameZh;
    }

    public void setRoomTypeNameZh(String roomTypeNameZh) {
        this.roomTypeNameZh = roomTypeNameZh;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }
}
