package com.cap.MolvenoLakeResort.controller.business.room;

import com.cap.MolvenoLakeResort.payload.request.room.RoomTypeRequestDto;
import com.cap.MolvenoLakeResort.payload.response.room.RoomTypeResponseDto;
import com.cap.MolvenoLakeResort.service.business.room.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roomType")
public class RoomTypeController {

    private final RoomTypeService roomTypeService;

    @Autowired
    public RoomTypeController(RoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }

    @GetMapping
    public ResponseEntity<List<RoomTypeResponseDto>> getAllRoomType() {
        List<RoomTypeResponseDto> roomTypeResponseDtos = roomTypeService.getAllRoomType();
        return ResponseEntity.ok(roomTypeResponseDtos);
    }

    @GetMapping("/forBookNow")
    public ResponseEntity<List<RoomTypeResponseDto>> getAllRoomType(@RequestParam String lang) {
        List<RoomTypeResponseDto> roomTypeResponseDtos = roomTypeService.getAllRoomType(lang);
        return ResponseEntity.ok(roomTypeResponseDtos);
    }

    @DeleteMapping("/{roomTypeName}")
//http://localhost:8080/roomType/
    public ResponseEntity<String> deleteRoomTypeByRoomTypeName(@PathVariable("roomTypeName") String roomTypeName) {
        String result = roomTypeService.deleteRoomTypeByRoomTypeName(roomTypeName);
        return ResponseEntity.ok(result);

    }

    @PostMapping()//http:localhost:8080/api/roomType
    public ResponseEntity<RoomTypeResponseDto> saveRoomTypeByRoomTypeName(@RequestBody RoomTypeRequestDto roomTypeRequestDto) {
        RoomTypeResponseDto roomTypeResponseDto = roomTypeService.saveRoomTypeByRoomTypeName(roomTypeRequestDto);
        return ResponseEntity.ok(roomTypeResponseDto);
    }

    @PutMapping("/editRomeTypeName/{roomTypeName}")
    public ResponseEntity<RoomTypeResponseDto> editRoomTypeByRoomTypeName(@RequestBody RoomTypeRequestDto roomTypeRequestDto, @PathVariable("roomTypeName") String roomTypeName) {
        RoomTypeResponseDto roomTypeResponseDto = roomTypeService.editRoomTypeByRoomTypeName(roomTypeRequestDto, roomTypeName);
        return ResponseEntity.ok(roomTypeResponseDto);
    }

    @GetMapping("/can-delete/{roomTypeId}")
    public ResponseEntity<Boolean> canDeleteRoomType(@PathVariable Long roomTypeId) {
        boolean canDelete = roomTypeService.canDeleteRoomType(roomTypeId);
        return ResponseEntity.ok(canDelete);
    }


}
