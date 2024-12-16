package com.cap.MolvenoLakeResort.payload.mappers;

import com.cap.MolvenoLakeResort.model.business.room.Room;
import com.cap.MolvenoLakeResort.model.media.Image;
import com.cap.MolvenoLakeResort.payload.request.room.RoomRequestDto;
import com.cap.MolvenoLakeResort.payload.response.room.DirtyRoomResponseDto;
import com.cap.MolvenoLakeResort.payload.response.room.RoomResponseDto;
import com.cap.MolvenoLakeResort.service.business.room.BedTypeServiceImpl;
import com.cap.MolvenoLakeResort.service.business.room.FacilitiesServiceImpl;
import com.cap.MolvenoLakeResort.service.business.room.RoomTypeServiceImpl;
import com.cap.MolvenoLakeResort.service.media.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoomMapper {

    private final RoomTypeServiceImpl roomTypeServiceImpl;
    private final BedTypeServiceImpl bedTypeServiceImpl;
    private final FacilitiesServiceImpl facilitiesServiceImpl;
    private final ImageServiceImpl imageServiceImpl;

    @Autowired
    public RoomMapper(RoomTypeServiceImpl roomTypeServiceImpl, BedTypeServiceImpl bedTypeServiceImpl, FacilitiesServiceImpl facilitiesServiceImpl, ImageServiceImpl imageServiceImpl) {
        this.roomTypeServiceImpl = roomTypeServiceImpl;
        this.bedTypeServiceImpl = bedTypeServiceImpl;
        this.facilitiesServiceImpl = facilitiesServiceImpl;
        this.imageServiceImpl = imageServiceImpl;
    }

    public List<RoomResponseDto> mapRoomListToRoomResponseDtoList(List<Room> roomList,  String lang) {

        List<RoomResponseDto> roomResponseDtos = new ArrayList<>();

        for (Room room : roomList) {
            RoomResponseDto roomResponseDto = new RoomResponseDto();
            roomResponseDto.setRoomId(room.getRoomId());
            roomResponseDto.setRoomNumber(room.getRoomNumber());
            roomResponseDto.setRoomType(room.getRoomType().getRoomTypeName());
            if ("zh".equalsIgnoreCase(lang)) {
                roomResponseDto.setRoomType(room.getRoomType().getRoomTypeNameZh());
                System.out.println(room.getRoomType().getRoomTypeNameZh());


            } else {
                roomResponseDto.setRoomType(room.getRoomType().getRoomTypeName());
            }

            roomResponseDto.setCleaned(room.isCleaned());
            roomResponseDto.setPrice(room.getPrice());
            roomResponseDto.setRoomBedType(room.getBedTypeList().stream().map(s-> s.getBedTypeName()).toList());//
            if ("zh".equalsIgnoreCase(lang)) {
                roomResponseDto.setRoomBedType(room.getBedTypeList().stream().map(s-> s.getBedTypeNameZh()).toList());//


            } else {
                roomResponseDto.setRoomBedType(room.getBedTypeList().stream().map(s-> s.getBedTypeName()).toList());//

            }


            roomResponseDto.setCountOfAdult(room.getCountOfAdult());
            roomResponseDto.setCountOfChild(room.getCountOfChild());
            roomResponseDto.setSmoking(room.isSmoking());
            roomResponseDto.setFacilitiesList(room.getFacilitiesList().stream().map(s-> s.getFacilitiesName()).toList());
            if ("zh".equalsIgnoreCase(lang)) {
                roomResponseDto.setFacilitiesList(room.getFacilitiesList().stream().map(s-> s.getFacilitiesNameZh()).toList());

            } else {
                roomResponseDto.setFacilitiesList(room.getFacilitiesList().stream().map(s-> s.getFacilitiesName()).toList());

            }
            roomResponseDto.setThFloor(room.getThFloor());
            roomResponseDto.setImageUrlList(room.getImageList().stream().map(Image::getImageUrl).collect(Collectors.toList()));

            if ("zh".equalsIgnoreCase(lang)) {
                roomResponseDto.setDescription(room.getDescriptionZh());

            } else {
                roomResponseDto.setDescription(room.getDescription());

            }
            roomResponseDto.setDisabled(room.isDisabled());
            roomResponseDtos.add(roomResponseDto);

        }
        return roomResponseDtos;

    }

    public RoomResponseDto mapRoomToRoomResponseDto(Room savedRoom) {
        RoomResponseDto roomResponseDto = new RoomResponseDto();
        roomResponseDto.setRoomId(savedRoom.getRoomId());
        roomResponseDto.setRoomNumber(savedRoom.getRoomNumber());
        roomResponseDto.setRoomType(savedRoom.getRoomType().getRoomTypeName());
        roomResponseDto.setCleaned(savedRoom.isCleaned());
        roomResponseDto.setPrice(savedRoom.getPrice());
        roomResponseDto.setRoomBedType(savedRoom.getBedTypeList().stream().map(s-> s.getBedTypeName()).toList());
        roomResponseDto.setCountOfAdult(savedRoom.getCountOfAdult());
        roomResponseDto.setCountOfChild(savedRoom.getCountOfChild());
        roomResponseDto.setSmoking(savedRoom.isSmoking());
        roomResponseDto.setFacilitiesList(savedRoom.getFacilitiesList().stream().map(s-> s.getFacilitiesName()).toList());
        roomResponseDto.setThFloor(savedRoom.getThFloor());
        roomResponseDto.setImageUrlList(savedRoom.getImageList().stream().map(Image::getImageUrl).collect(Collectors.toList()));
        roomResponseDto.setDescription(savedRoom.getDescription());
        roomResponseDto.setDisabled(savedRoom.isDisabled());
        return roomResponseDto;
    }

    public RoomResponseDto mapRoomToRoomResponseDto(Room savedRoom, String lang) {
        RoomResponseDto roomResponseDto = new RoomResponseDto();
        roomResponseDto.setRoomId(savedRoom.getRoomId());
        roomResponseDto.setRoomNumber(savedRoom.getRoomNumber());

        roomResponseDto.setCleaned(savedRoom.isCleaned());
        roomResponseDto.setPrice(savedRoom.getPrice());

        if ("zh".equalsIgnoreCase(lang)) {
            roomResponseDto.setRoomBedType(savedRoom.getBedTypeList().stream().map(s-> s.getBedTypeNameZh()).toList());
            roomResponseDto.setFacilitiesList(savedRoom.getFacilitiesList().stream().map(s-> s.getFacilitiesNameZh()).toList());
            roomResponseDto.setRoomType(savedRoom.getRoomType().getRoomTypeNameZh());
            roomResponseDto.setDescription(savedRoom.getDescriptionZh());

        } else {
            roomResponseDto.setRoomBedType(savedRoom.getBedTypeList().stream().map(s-> s.getBedTypeName()).toList());
            roomResponseDto.setFacilitiesList(savedRoom.getFacilitiesList().stream().map(s-> s.getFacilitiesName()).toList());
            roomResponseDto.setRoomType(savedRoom.getRoomType().getRoomTypeName());
            roomResponseDto.setDescription(savedRoom.getDescription());
        }



        roomResponseDto.setCountOfAdult(savedRoom.getCountOfAdult());
        roomResponseDto.setCountOfChild(savedRoom.getCountOfChild());
        roomResponseDto.setSmoking(savedRoom.isSmoking());
        roomResponseDto.setThFloor(savedRoom.getThFloor());
        roomResponseDto.setImageUrlList(savedRoom.getImageList().stream().map(Image::getImageUrl).collect(Collectors.toList()));
        roomResponseDto.setDisabled(savedRoom.isDisabled());
        return roomResponseDto;
    }

    public Room mapRoomRequestDtoToRoom(RoomRequestDto roomRequestDto) {
        Room room = new Room();
        room.setRoomId(roomRequestDto.getRoomId());
        room.setRoomNumber(roomRequestDto.getRoomNumber());
        if (roomRequestDto.getRoomId()!=0){
            room.setRoomId(roomRequestDto.getRoomId());
        }

        room.setRoomType(roomTypeServiceImpl.getRoomTypeByRoomTypeName(roomRequestDto.getRoomType()));
        room.setCleaned(roomRequestDto.isCleaned());
        room.setPrice(roomRequestDto.getPrice());
        room.setSmoking(roomRequestDto.isSmoking());
        room.setBedTypeList(bedTypeServiceImpl.getBedTypeList(roomRequestDto.getRoomBedTypeList()));
        room.setFacilitiesList(facilitiesServiceImpl.getFacilitiesListByFacilitiesNames(roomRequestDto.getFacilitiesList()));
        room.setCountOfAdult(roomRequestDto.getCountOfAdult());
        room.setCountOfChild(roomRequestDto.getCountOfChild());
//        room.getImageList().add(imageService.findImageByUrl(roomRequestDto.getImageurl()));
        room.setThFloor(roomRequestDto.getFloor());
        room.setDisabled(roomRequestDto.isDisabled());
        room.setDescription(roomRequestDto.getDescription());


        return room;

    }


    public List<DirtyRoomResponseDto> mapDirtyRoomToDirtyRoomResponse(List<Room> byCleanedFalse) {

        List<DirtyRoomResponseDto> dirtyRoomResponseDtos = new ArrayList<>();

        for(Room room :byCleanedFalse){

            DirtyRoomResponseDto dirtyRoomResponseDto = new DirtyRoomResponseDto();
            dirtyRoomResponseDto.setCleaned(room.isCleaned());
            dirtyRoomResponseDto.setRoomNumber(room.getRoomNumber());
            dirtyRoomResponseDto.setRoomId(room.getRoomId());
            dirtyRoomResponseDto.setThFloor(room.getThFloor());

            dirtyRoomResponseDtos.add(dirtyRoomResponseDto);


        }
        return dirtyRoomResponseDtos;
    }
}
