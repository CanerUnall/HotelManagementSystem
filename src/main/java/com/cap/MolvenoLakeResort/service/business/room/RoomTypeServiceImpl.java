package com.cap.MolvenoLakeResort.service.business.room;

import com.cap.MolvenoLakeResort.model.business.room.RoomType;
import com.cap.MolvenoLakeResort.payload.mappers.RoomTypeMapper;
import com.cap.MolvenoLakeResort.payload.request.room.RoomTypeRequestDto;
import com.cap.MolvenoLakeResort.payload.response.room.RoomTypeResponseDto;
import com.cap.MolvenoLakeResort.repository.business.room.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomTypeServiceImpl implements RoomTypeService{


    private final RoomTypeRepository roomTypeRepository;
    private final RoomTypeMapper roomTypeMapper;

    @Autowired
    public RoomTypeServiceImpl(RoomTypeRepository roomTypeRepository, RoomTypeMapper roomTypeMapper) {
        this.roomTypeRepository = roomTypeRepository;
        this.roomTypeMapper = roomTypeMapper;
    }

    @Override
    public List<RoomTypeResponseDto> getAllRoomType() {
        List<RoomType> roomTypeList = roomTypeRepository.findAll();
        List<RoomTypeResponseDto> roomTypeResponseDtos = roomTypeMapper.mapRoomTypeListToRoomTypeResponseDtoList(roomTypeList);
        return roomTypeResponseDtos;
    }

    @Override
    public List<RoomTypeResponseDto> getAllRoomType(String lang) {
        List<RoomType> roomTypeList = roomTypeRepository.findAll();
        List<RoomTypeResponseDto> roomTypeResponseDtos = roomTypeMapper.mapRoomTypeListToRoomTypeResponseDtoList(roomTypeList,lang);
        return roomTypeResponseDtos;
    }
    @Override
    public String deleteRoomTypeById(Long id) {
        if (roomTypeRepository.findById(id).isPresent()) {
            roomTypeRepository.deleteById(id);
            return "Room Type deleted successfully!";
        } else {
            return "Room Type Not Found";

        }
    }

    @Override
    public RoomType getRoomTypeByRoomTypeName(String name){
        if (roomTypeRepository.findByRoomTypeName(name).isPresent()){
            return roomTypeRepository.findByRoomTypeName(name).get();
        }
        return null;
    }

    @Override
    public RoomType saveRoomType(RoomType roomType) {
        Optional<RoomType> optionalRoomType = roomTypeRepository.findById(roomType.getRoomTypeId());

        if (roomTypeRepository.findByRoomTypeName(roomType.getRoomTypeName()).isPresent()){
            roomType.setRoomTypeId(roomTypeRepository.findByRoomTypeName(roomType.getRoomTypeName()).get().getRoomTypeId());
        }
        return roomTypeRepository.save(roomType);
    }

    @Override
    public String deleteRoomTypeByRoomTypeName(String roomTypeName) {
        if (roomTypeRepository.findByRoomTypeName(roomTypeName).isPresent()) {
            roomTypeRepository.deleteById(roomTypeRepository.findByRoomTypeName(roomTypeName).get().getRoomTypeId());
            return "RoomType deleted successfully!";

        } else {
            return "RoomType Not Found";
        }
    }

    @Override
    public RoomTypeResponseDto saveRoomTypeByRoomTypeName(RoomTypeRequestDto roomTypeRequestDto) {
        RoomType roomType  = roomTypeMapper.mapRoomTypeRequestToRoomType(roomTypeRequestDto);
        Optional<RoomType> roomType1= roomTypeRepository.findByRoomTypeName(roomTypeRequestDto.getRoomTypeName());
        if (roomType1.isPresent()){
            roomType.setRoomTypeId(roomType1.get().getRoomTypeId());
        }

        RoomType savedRoomType= roomTypeRepository.save(roomType);
        RoomTypeResponseDto roomTypeResponseDto = roomTypeMapper.mapRoomToRoomTypeResponseDto(savedRoomType);
        return roomTypeResponseDto;


    }

    @Override
    public RoomTypeResponseDto editRoomTypeByRoomTypeName(RoomTypeRequestDto roomTypeRequestDto, String roomTypeName) {
        RoomType roomType = roomTypeRepository.findByRoomTypeName(roomTypeName).get();
        RoomType roomTypeToRegister = roomTypeMapper.mapRoomTypeRequestToRoomType(roomTypeRequestDto);
        roomTypeToRegister.setRoomTypeName(roomType.getRoomTypeName());
        RoomType savedRoomType = roomTypeRepository.save(roomTypeToRegister);


        return roomTypeMapper.mapRoomTypeToRoomTypeResponseDto(savedRoomType);
    }

    @Override
    public boolean canDeleteRoomType(Long roomTypeId) {
        RoomType roomType = roomTypeRepository.findById(roomTypeId).orElse(null);
        if (roomType != null && roomType.getRoomList().isEmpty()) {
            return true;
        }
        return false;
    }
}





