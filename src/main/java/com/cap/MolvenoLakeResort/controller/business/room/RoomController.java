package com.cap.MolvenoLakeResort.controller.business.room;

import com.cap.MolvenoLakeResort.payload.request.room.DirtyRoomRequestDto;
import com.cap.MolvenoLakeResort.payload.request.room.RoomRequestDto;
import com.cap.MolvenoLakeResort.payload.response.room.DirtyRoomResponseDto;
import com.cap.MolvenoLakeResort.payload.request.room.RoomSearchFilter;
import com.cap.MolvenoLakeResort.payload.response.room.RoomResponseDto;
import com.cap.MolvenoLakeResort.service.business.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    //private final ImageService imageService;
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }


    @GetMapping
    public ResponseEntity<List<RoomResponseDto>> getAllRooms(@RequestParam String lang) {
        List<RoomResponseDto> roomResponseDtos = roomService.getAllRoom(lang);
        return ResponseEntity.ok(roomResponseDtos);
    }

//    @GetMapping("/getRoom/{roomNumber}")
//    public ResponseEntity<RoomResponseDto> getRoomByRoomNumber(@PathVariable String roomNumber){
//        return ResponseEntity.ok(roomService.getRoomByRoomNumber(roomNumber));
//    }
//
//
//    @PostMapping("/saveRoom")//http://localhost:8080/api/room/saveRoom
//    public ResponseEntity<Long> saveRoomByRoomNumber(@RequestBody RoomRequestDto roomRequestDto){
//        System.out.println("roomRequestDto.getRoomNumber() = " + roomRequestDto.getFloor());
//        RoomResponseDto roomResponseDto = roomService.saveRoom(roomRequestDto);
//        return ResponseEntity.ok(roomResponseDto.getRoomId());
//    }


    @PostMapping
    public RoomResponseDto createRoom(@RequestParam("roomId") Long roomId,
                                      @RequestParam("roomNumber") String roomNumber,
                                      @RequestParam("roomType") String roomType,
                                      @RequestParam("countOfAdult") int countOfAdult,
                                      @RequestParam("countOfChild") int countOfChild,
                                      @RequestParam("floor") String floor,
                                      @RequestParam("price") double price,
                                      @RequestParam("facilitiesList") String facilitiesList,
                                      @RequestParam("roomBedTypeList") String roomBedTypeList,
                                      @RequestParam("cleaned") boolean cleaned,
                                      @RequestParam("smoking") boolean smoking,
                                      @RequestParam("description") String description,
                                      @RequestParam("disabled") boolean disabled,
                                      @RequestParam(value = "photos", required = false) MultipartFile[] photos) {

        RoomRequestDto roomRequestDto = new RoomRequestDto();
        roomRequestDto.setRoomId(roomId);
        roomRequestDto.setRoomNumber(roomNumber);
        roomRequestDto.setRoomType(roomType);
        roomRequestDto.setCountOfAdult(countOfAdult);
        roomRequestDto.setCountOfChild(countOfChild);
        roomRequestDto.setFloor(floor);
        roomRequestDto.setPrice(price);
        roomRequestDto.setFacilitiesList(Arrays.asList(facilitiesList.split(",")));
        roomRequestDto.setRoomBedTypeList(Arrays.asList(roomBedTypeList.split(",")));
        roomRequestDto.setCleaned(cleaned);
        roomRequestDto.setSmoking(smoking);
        roomRequestDto.setDescription(description);

        // Null kontrolü yaparak, photos parametresini boş bir listeye ayarlayın
        if (photos != null) {
            roomRequestDto.setPhotos(Arrays.asList(photos));
        } else {
            roomRequestDto.setPhotos(Collections.emptyList());
        }

        roomRequestDto.setDisabled(disabled);

        return roomService.createRoom(roomRequestDto);
    }





    @PutMapping("/edit")
    public ResponseEntity<RoomResponseDto> editRoom(@RequestBody RoomRequestDto roomRequestDto ){
        RoomResponseDto roomResponseDto = roomService.editRoom(roomRequestDto);

        return ResponseEntity.ok(roomResponseDto);
    }


    @DeleteMapping("/deleteById/{roomId}")//http://localhost:8080/reservation/deleteByRoomNumber/
    public ResponseEntity<String> deleteRoomByRoomId(@PathVariable("roomId") Long roomId){
        String result = roomService.deleteRoomById(roomId);
        return ResponseEntity.ok(result);

    }
    @GetMapping("/dirtyRooms")
    public ResponseEntity<List<DirtyRoomResponseDto>> getAllDirtyRooms() {
        List<DirtyRoomResponseDto> dirtyRoomResponseDtos = roomService.getAllDirtyRoom();
        return ResponseEntity.ok(dirtyRoomResponseDtos);
    }

    @PutMapping("/editRoomStatus")
    public ResponseEntity<String> editRoomStatus(@RequestBody DirtyRoomRequestDto dirtyRoomRequestDto ){
        String status = roomService.editRoomStatus(dirtyRoomRequestDto);

        return ResponseEntity.ok(status);
    }




    @PostMapping("/search/{lang}")
    public ResponseEntity<List<RoomResponseDto>> searchAvailableRooms(@RequestBody RoomSearchFilter roomSearchFilter,@PathVariable("lang") String lang) {

        System.out.println("Received search filter: " + roomSearchFilter);
        return ResponseEntity.ok(roomService.findAvailableRooms(roomSearchFilter,lang));
    }

    @GetMapping("/{roomNumber}")
    public ResponseEntity<RoomResponseDto> getRoomByRoomNumber(@PathVariable("roomNumber") String roomNumber, @RequestParam("lang") String lang){
        return ResponseEntity.ok(roomService.getRoomByNumber(roomNumber,lang));
    }

    @GetMapping("/table/{roomNumber}")
    public ResponseEntity<RoomResponseDto> getRoomByRoomNumberForTable(@PathVariable("roomNumber") String roomNumber){
        return ResponseEntity.ok(roomService.getRoomByNumber(roomNumber));
    }

    @GetMapping("/checkRoomNumber")
    public ResponseEntity<Boolean> checkRoomNumberUnique(@RequestParam String roomNumber) {
        boolean isUnique = roomService.isRoomNumberUnique(roomNumber);
        return ResponseEntity.ok(isUnique);
    }

    @GetMapping("/can-delete/{roomId}")
    public ResponseEntity<Boolean> canDeleteRoom(@PathVariable Long roomId) {
        boolean canDelete = roomService.canDeleteRoom(roomId);
        return ResponseEntity.ok(canDelete);
    }


}
