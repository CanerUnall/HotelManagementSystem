package com.cap.MolvenoLakeResort.payload.request.room;

public class DirtyRoomRequestDto {
    private Long roomId;
    private boolean cleaned;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public boolean isCleaned() {
        return cleaned;
    }

    public void setCleaned(boolean cleaned) {
        this.cleaned = cleaned;
    }
}
