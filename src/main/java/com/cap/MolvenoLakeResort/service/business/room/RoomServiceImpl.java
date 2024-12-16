package com.cap.MolvenoLakeResort.service.business.room;

import com.cap.MolvenoLakeResort.model.business.reservation.Reservation;
import com.cap.MolvenoLakeResort.model.business.room.BedType;
import com.cap.MolvenoLakeResort.model.business.room.Facilities;
import com.cap.MolvenoLakeResort.model.business.room.Room;
import com.cap.MolvenoLakeResort.model.media.Image;
import com.cap.MolvenoLakeResort.payload.mappers.RoomMapper;
import com.cap.MolvenoLakeResort.payload.request.media.ImageRequestDto;
import com.cap.MolvenoLakeResort.payload.request.room.DirtyRoomRequestDto;
import com.cap.MolvenoLakeResort.payload.request.room.RoomRequestDto;
import com.cap.MolvenoLakeResort.payload.request.room.RoomSearchFilter;
import com.cap.MolvenoLakeResort.payload.response.room.DirtyRoomResponseDto;
import com.cap.MolvenoLakeResort.payload.response.room.RoomResponseDto;
import com.cap.MolvenoLakeResort.repository.business.reservation.ReservationRepository;
import com.cap.MolvenoLakeResort.repository.business.room.BedTypeRepository;
import com.cap.MolvenoLakeResort.repository.business.room.FacilitiesRepository;
import com.cap.MolvenoLakeResort.repository.business.room.RoomRepository;
import com.cap.MolvenoLakeResort.repository.business.room.RoomTypeRepository;
import com.cap.MolvenoLakeResort.service.media.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {
    private final String roomUploadDir = "src/main/resources/static/img/rooms/";
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final ImageService imageService;
    private final RoomTypeRepository roomTypeRepository;
    private final FacilitiesRepository facilitiesRepository;
    private final BedTypeRepository bedTypeRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, RoomMapper roomMapper, ImageService imageService,
                           RoomTypeRepository roomTypeRepository, FacilitiesRepository facilitiesRepository,
                           BedTypeRepository bedTypeRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
        this.imageService = imageService;
        this.roomTypeRepository = roomTypeRepository;
        this.facilitiesRepository = facilitiesRepository;
        this.bedTypeRepository = bedTypeRepository;
        this.reservationRepository = reservationRepository;
    }


    @Override
    public RoomResponseDto createRoom(RoomRequestDto roomRequestDto) {


        Room room = roomMapper.mapRoomRequestDtoToRoom(roomRequestDto);

        //fotograflari setliyorum. burasi kafa karistirici
        if (!roomRequestDto.getPhotos().isEmpty()) {
            List<Image> images = roomRequestDto.getPhotos().stream()
                    .map(photo -> {
                        ImageRequestDto imageRequestDto = new ImageRequestDto();
                        imageRequestDto.setName(photo.getOriginalFilename());
                        try {
                            imageRequestDto.setUrl(Base64.getEncoder().encodeToString(photo.getBytes()));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        return imageService.saveImage(imageRequestDto, "ROOM");
                    })
                    .collect(Collectors.toList());


            room.setImageList(images);
        } else if (roomRepository.findById(roomRequestDto.getRoomId()).isPresent()) {
            room.setImageList(roomRepository.findById(roomRequestDto.getRoomId()).get().getImageList());
        }


        Room savedRoom = roomRepository.save(room);


        return roomMapper.mapRoomToRoomResponseDto(savedRoom);
    }


    @Override
    public List<RoomResponseDto> getAllRoom(String lang) {
        List<Room> roomList = roomRepository.findAll();
        return roomMapper.mapRoomListToRoomResponseDtoList(roomList, lang);
    }

    public RoomResponseDto getRoomByRoomNumber(String roomNumber) {
        Room room = roomRepository.findByRoomNumber(roomNumber).get();
        return roomMapper.mapRoomToRoomResponseDto(room);


    }

    @Override
    public RoomResponseDto editRoom(RoomRequestDto roomRequestDto) {
        Optional<Room> room = roomRepository.findByRoomNumber(roomRequestDto.getRoomNumber());

        if (room.isPresent()) {
            Room roomToCreate = roomMapper.mapRoomRequestDtoToRoom(roomRequestDto);

            Room savedRoom = roomRepository.save(roomToCreate);

            return roomMapper.mapRoomToRoomResponseDto(savedRoom);
        }

        return null;
    }

    @Override
    public String deleteRoomById(Long roomId) {
        roomRepository.deleteById(roomId);
        return "success";
    }

    @Override
    public RoomResponseDto getRoomByNumber(String roomNumber, String lang) {
        Optional<Room> optionalRoom= roomRepository.findByRoomNumber(roomNumber);
        if (optionalRoom.isPresent()){
            return roomMapper.mapRoomToRoomResponseDto(optionalRoom.get(), lang);
        }
        return null;
    }

    @Override
    public RoomResponseDto getRoomByNumber(String roomNumber) {
        Optional<Room> optionalRoom= roomRepository.findByRoomNumber(roomNumber);
        if (optionalRoom.isPresent()){
            return roomMapper.mapRoomToRoomResponseDto(optionalRoom.get());
        }
        return null;
    }

    @Override
    public List<RoomResponseDto> findAvailableRooms(RoomSearchFilter roomSearchFilter, String lang) {
        List<Room> availableRooms = roomRepository.findAvailableRooms(roomSearchFilter.getCheckIn(), roomSearchFilter.getCheckOut());
        return availableRooms.stream()
                .filter(room -> filterRoom(room, roomSearchFilter, lang))
                .map(room -> roomMapper.mapRoomToRoomResponseDto(room, lang)) // Lambda ile iki parametre gönderiliyor
                .collect(Collectors.toList());
    }



    private boolean filterRoom(Room room, RoomSearchFilter roomSearchFilter, String lang) {
        System.out.println("Filtering room: " + room);
        System.out.println("With filter: " + roomSearchFilter);

        String roomTypeName = lang.equals("zh") ? room.getRoomType().getRoomTypeNameZh() : room.getRoomType().getRoomTypeName();
        if (roomSearchFilter.getRoomType() != null && !roomSearchFilter.getRoomType().isEmpty() && !roomTypeName.equals(roomSearchFilter.getRoomType())) {
            System.out.println("Room type does not match: " + roomTypeName + " != " + roomSearchFilter.getRoomType());
            return false;
        }


        if (roomSearchFilter.getSmoking() != null) {
            if (roomSearchFilter.getSmoking()) {
                // Kullanıcı sadece sigara içilebilen odaları görmek istiyor
                if (!room.isSmoking()) {
                    System.out.println("Smoking preference does not match: " + room.isSmoking() + " != " + roomSearchFilter.getSmoking());
                    return false;
                }
            }
        }
        // Facilities filter based on language
        List<String> facilitiesList = lang.equals("zh") ?
                room.getFacilitiesList().stream().map(Facilities::getFacilitiesNameZh).collect(Collectors.toList()) :
                room.getFacilitiesList().stream().map(Facilities::getFacilitiesName).collect(Collectors.toList());

        if (roomSearchFilter.getFacilities() != null && !roomSearchFilter.getFacilities().isEmpty() && !facilitiesList.containsAll(roomSearchFilter.getFacilities())) {
            System.out.println("Facilities do not match: " + facilitiesList + " != " + roomSearchFilter.getFacilities());
            return false;
        }


        // Bed Type filter based on language
        List<String> bedTypeList = lang.equals("zh") ?
                room.getBedTypeList().stream().map(BedType::getBedTypeNameZh).collect(Collectors.toList()) :
                room.getBedTypeList().stream().map(BedType::getBedTypeName).collect(Collectors.toList());

        if (roomSearchFilter.getBedTypes() != null && !roomSearchFilter.getBedTypes().isEmpty() && !bedTypeList.containsAll(roomSearchFilter.getBedTypes())) {
            System.out.println("Bed types do not match: " + bedTypeList + " != " + roomSearchFilter.getBedTypes());
            return false;
        }

        if (roomSearchFilter.getFloor() != null && !roomSearchFilter.getFloor().isEmpty() && !room.getThFloor().equals(roomSearchFilter.getFloor())) {
            System.out.println("Floor does not match: " + room.getThFloor() + " != " + roomSearchFilter.getFloor());
            return false;
        }
        // Disabled friendly filter: Only apply if it's specified by the user
        if (roomSearchFilter.getDisabledFriendly() != null) {
            if (room.isDisabled() != roomSearchFilter.getDisabledFriendly()) {
                System.out.println("Disabled friendly does not match: " + room.isDisabled() + " != " + roomSearchFilter.getDisabledFriendly());
                return false;
            }
        }

        // Adult count filter
        if (roomSearchFilter.getAdults() != null && roomSearchFilter.getAdults() > room.getCountOfAdult()) {
            System.out.println("Adult count does not match: " + room.getCountOfAdult() + " < " + roomSearchFilter.getAdults());
            return false;
        }

        // Child count filter
        if (roomSearchFilter.getChildren() != null && roomSearchFilter.getChildren() > room.getCountOfChild()) {
            System.out.println("Child count does not match: " + room.getCountOfChild() + " < " + roomSearchFilter.getChildren());
            return false;
        }

        // Cleaned room filter
        if (!room.isCleaned()) {
            System.out.println("Room is not cleaned: " + room.isCleaned());
            return false;
        }

        return true;
    }



    @Override
    public List<DirtyRoomResponseDto> getAllDirtyRoom() {


        return roomMapper.mapDirtyRoomToDirtyRoomResponse(roomRepository.findByCleanedFalse());
    }

    @Override
    public String editRoomStatus(DirtyRoomRequestDto dirtyRoomRequestDto) {

        if (roomRepository.findById(dirtyRoomRequestDto.getRoomId()).isPresent()) {
            Room room = roomRepository.findById(dirtyRoomRequestDto.getRoomId()).get();
            room.setCleaned(dirtyRoomRequestDto.isCleaned());
            roomRepository.save(room);

            return "succes!";
        }
        return "";
    }

    @Override
    public boolean isRoomNumberUnique(String roomNumber) {
        // Room Number'ın var olup olmadığını kontrol ediyoruz
        return !roomRepository.existsByRoomNumberIgnoreCase(roomNumber);
    }

    @Override
    public boolean canDeleteRoom(Long roomId) {
        Room room = roomRepository.findById(roomId).orElse(null);
        if (room != null) {
            List<Reservation> activeReservations = reservationRepository.findActiveOrFutureReservationsByRoom(room);
            return activeReservations.isEmpty();
        }
        return true;
    }
}
