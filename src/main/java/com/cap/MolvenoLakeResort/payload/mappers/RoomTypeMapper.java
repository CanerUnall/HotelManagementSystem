package com.cap.MolvenoLakeResort.payload.mappers;

import com.cap.MolvenoLakeResort.model.business.room.RoomType;
import com.cap.MolvenoLakeResort.payload.request.room.RoomTypeRequestDto;
import com.cap.MolvenoLakeResort.payload.response.room.RoomTypeResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class RoomTypeMapper {
    public List<RoomTypeResponseDto> mapRoomTypeListToRoomTypeResponseDtoList(List<RoomType> roomTypeList) {
        List<RoomTypeResponseDto> roomTypeResponseDtos = new ArrayList<>();
        for (RoomType roomType : roomTypeList) {
            RoomTypeResponseDto roomTypeResponseDto = new RoomTypeResponseDto();
            roomTypeResponseDto.setRoomTypeId(roomType.getRoomTypeId());
            roomTypeResponseDto.setRoomTypeName(roomType.getRoomTypeName());
            roomTypeResponseDto.setRoomTypeNameZh(roomType.getRoomTypeNameZh());
            roomTypeResponseDtos.add(roomTypeResponseDto);
        }
        return roomTypeResponseDtos;
    }

    public List<RoomTypeResponseDto> mapRoomTypeListToRoomTypeResponseDtoList(List<RoomType> roomTypeList, String lang) {
        List<RoomTypeResponseDto> roomTypeResponseDtos = new ArrayList<>();
        for (RoomType roomType : roomTypeList) {
            RoomTypeResponseDto roomTypeResponseDto = new RoomTypeResponseDto();
            roomTypeResponseDto.setRoomTypeId(roomType.getRoomTypeId());
            if ("zh".equalsIgnoreCase(lang)) {

                roomTypeResponseDto.setRoomTypeName(roomType.getRoomTypeNameZh());

            } else {
                roomTypeResponseDto.setRoomTypeName(roomType.getRoomTypeName());
            }

            roomTypeResponseDtos.add(roomTypeResponseDto);
        }
        return roomTypeResponseDtos;
    }

    public RoomType mapRoomTypeRequestToRoomType(RoomTypeRequestDto roomTypeRequestDto) {
        RoomType roomType = new RoomType();
        roomType.setRoomTypeId(roomTypeRequestDto.getRoomTypeId());
        roomType.setRoomTypeName(roomTypeRequestDto.getRoomTypeName());
        roomType.setRoomTypeNameZh(roomTypeRequestDto.getRoomTypeNameZh());
        return roomType;


    }

    public RoomTypeResponseDto mapRoomToRoomTypeResponseDto(RoomType savedRoomType) {
        RoomTypeResponseDto roomTypeResponseDto = new RoomTypeResponseDto();

         roomTypeResponseDto.setRoomTypeName(savedRoomType.getRoomTypeName());
         roomTypeResponseDto.setRoomTypeNameZh(savedRoomType.getRoomTypeNameZh());
       return roomTypeResponseDto;

    }

    public RoomTypeResponseDto mapRoomTypeToRoomTypeResponseDto(RoomType savedRoomType) {
        RoomTypeResponseDto roomTypeResponseDto = new RoomTypeResponseDto();

        roomTypeResponseDto.setRoomTypeName(savedRoomType.getRoomTypeName());
        roomTypeResponseDto.setRoomTypeNameZh(savedRoomType.getRoomTypeNameZh());
    return roomTypeResponseDto;
    }
}
