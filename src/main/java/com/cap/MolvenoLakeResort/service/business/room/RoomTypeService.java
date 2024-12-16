package com.cap.MolvenoLakeResort.service.business.room;

import com.cap.MolvenoLakeResort.model.business.room.RoomType;
import com.cap.MolvenoLakeResort.payload.request.room.RoomTypeRequestDto;
import com.cap.MolvenoLakeResort.payload.response.room.RoomTypeResponseDto;

import java.util.List;

public interface RoomTypeService {
    List<RoomTypeResponseDto> getAllRoomType();
    List<RoomTypeResponseDto> getAllRoomType(String lang);

    String deleteRoomTypeById(Long id);

    RoomType getRoomTypeByRoomTypeName(String name);

    RoomType saveRoomType(RoomType roomType);

    String deleteRoomTypeByRoomTypeName(String roomTypeName);

    RoomTypeResponseDto saveRoomTypeByRoomTypeName(RoomTypeRequestDto roomTypeRequestDto);

    RoomTypeResponseDto editRoomTypeByRoomTypeName(RoomTypeRequestDto roomTypeRequestDto, String roomTypeName);

    boolean canDeleteRoomType(Long roomTypeId);
}
