package com.cap.MolvenoLakeResort.service.business.room;

import com.cap.MolvenoLakeResort.payload.request.room.DirtyRoomRequestDto;
import com.cap.MolvenoLakeResort.payload.request.room.RoomRequestDto;
import com.cap.MolvenoLakeResort.payload.request.room.RoomSearchFilter;
import com.cap.MolvenoLakeResort.payload.response.room.DirtyRoomResponseDto;
import com.cap.MolvenoLakeResort.payload.response.room.RoomResponseDto;

import java.util.List;

public interface RoomService {
    RoomResponseDto createRoom(RoomRequestDto roomRequestDto);

    List<RoomResponseDto> getAllRoom(String lang);

    RoomResponseDto editRoom(RoomRequestDto roomRequestDto);

    String deleteRoomById(Long roomId);

    List<RoomResponseDto> findAvailableRooms(RoomSearchFilter roomSearchFilter,String lang);


    RoomResponseDto getRoomByNumber(String roomNumber, String lang);
    RoomResponseDto getRoomByNumber(String roomNumber);
    List<DirtyRoomResponseDto> getAllDirtyRoom();

    String editRoomStatus(DirtyRoomRequestDto dirtyRoomRequestDto);

    boolean isRoomNumberUnique(String roomNumber);

    boolean canDeleteRoom(Long roomId);
}
