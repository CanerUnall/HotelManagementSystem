package com.cap.MolvenoLakeResort.payload.response.room;

public class DirtyRoomResponseDto {
    private Long roomId;
    private String roomNumber;
    private boolean cleaned;
    private String thFloor;

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public boolean isCleaned() {
        return cleaned;
    }

    public void setCleaned(boolean cleaned) {
        this.cleaned = cleaned;
    }

    public String getThFloor() {
        return thFloor;
    }

    public void setThFloor(String thFloor) {
        this.thFloor = thFloor;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
