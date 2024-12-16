package com.cap.MolvenoLakeResort.payload.mappers;

import com.cap.MolvenoLakeResort.model.business.reservation.Reservation;
import com.cap.MolvenoLakeResort.model.user.User;
import com.cap.MolvenoLakeResort.payload.request.reservation.ReservationRequest;
import com.cap.MolvenoLakeResort.payload.request.reservation.ReservationRequestDto;
import com.cap.MolvenoLakeResort.payload.request.user.UserRequestDto;
import com.cap.MolvenoLakeResort.payload.response.reservation.ReservationResponse;
import com.cap.MolvenoLakeResort.payload.response.reservation.ReservationResponseDto;
import com.cap.MolvenoLakeResort.payload.response.user.UserResponseDto;
import com.cap.MolvenoLakeResort.repository.business.room.RoomRepository;
import com.cap.MolvenoLakeResort.repository.user.UserRepository;
import com.cap.MolvenoLakeResort.service.business.room.RoomServiceImpl;
import com.cap.MolvenoLakeResort.service.user.UserRoleTypeServiceImpl;
import com.cap.MolvenoLakeResort.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ReservationMapper {
    private final UserServiceImpl userService;
    private final RoomRepository roomRepository;
    private final RoomServiceImpl roomService;
    private final UserRepository userRepository;
    private final UserRoleTypeServiceImpl userRoleTypeService;
    private final UserMapper userMapper;

    @Autowired
    public ReservationMapper(UserServiceImpl userService, RoomRepository roomRepository, RoomServiceImpl roomService, UserRepository userRepository, UserRoleTypeServiceImpl userRoleTypeService, UserMapper userMapper) {
        this.userService = userService;

        this.roomRepository = roomRepository;
        this.roomService = roomService;
        this.userRepository = userRepository;
        this.userRoleTypeService = userRoleTypeService;
        this.userMapper = userMapper;
    }


    public List<ReservationResponse> mapReservationListToReservationResponseDtoList(List<Reservation> reservationList) {

        List<ReservationResponse> reservationResponseList = new ArrayList<>();

        for (Reservation reservation : reservationList) {

            ReservationResponse reservationResponse = mapReservationToReservationResponse(reservation);


            reservationResponseList.add(reservationResponse);
        }

        return reservationResponseList;


    }

    public Reservation mapReservationRequestDtoToReservation(ReservationRequestDto reservationRequestDto) {
        Reservation reservation = new Reservation();
        reservation.setReservationId(reservationRequestDto.getReservationId());
        reservation.getGuestList().add(userService.findByLogin(reservationRequestDto.getGuestEmail()));
        if (roomRepository.findByRoomNumber(reservationRequestDto.getRoomNumber()).isPresent()) {
            reservation.setRoom(roomRepository.findByRoomNumber(reservationRequestDto.getRoomNumber()).get());
        }
        reservation.setStartDate(reservationRequestDto.getCheck_in());
        reservation.setEndDate(reservationRequestDto.getCheck_out());
        if (reservationRequestDto.getTransactionDateTime() == null) {
            reservation.setCreationTime(LocalDateTime.now());
        }

        reservation.setCreationTime(reservationRequestDto.getTransactionDateTime());

        return reservation;
    }

    public ReservationResponseDto mapReservationToReservationResponseDto(Reservation savedReservation) {
        ReservationResponseDto reservationResponseDto = new ReservationResponseDto();
        reservationResponseDto.setRoomNumber(savedReservation.getRoom().getRoomNumber());
        reservationResponseDto.setReservationId(savedReservation.getReservationId());
        reservationResponseDto.setCheck_in(savedReservation.getStartDate());
        reservationResponseDto.setCheck_out(savedReservation.getEndDate());
        reservationResponseDto.setGuestEmail(savedReservation.getGuestList().get(0).getEmail());

        reservationResponseDto.setTransactionDateTime(savedReservation.getCreationTime());
        return reservationResponseDto;
    }

    public Reservation mapReservationRequestToReservation(ReservationRequest reservationRequest) {
        System.out.println("mapper methodu calisti");
        Reservation reservation = new Reservation();
        reservation.setReservationId(reservationRequest.getReservationId());
        System.out.println(reservationRequest.getReservationId());
        if (roomRepository.findByRoomNumber(reservationRequest.getRoomNumber()).isPresent()) {
            reservation.setRoom(roomRepository.findByRoomNumber(reservationRequest.getRoomNumber()).get());
        }
        System.out.println(1);
        //misafirler icin liste olusturdum ve hepsini ekledim
        List<User> guests = new ArrayList<>();
        System.out.println(2);
        for (UserRequestDto user:reservationRequest.getGuestList()){
            if (user!=null && user.getUserName()!=null && user.getUserSurName() != null){
                System.out.println(3);
                if (user.getEmail()!=null && userService.findByLogin(user.getEmail())!=null){
                    //eger kullanici dbye kayitli ise onu direkt cagirdim
                    guests.add(userService.findByLogin(user.getEmail()));
                    continue;
                }
                System.out.println(4);
                Optional<User> userOptional = userRepository.findByUserNameAndUserSurNameAndPasswordIsNullAndEmailIsNullAndAddressIsNullAndPhoneNumberIsNullAndUserRoleTypeIsNull(user.getUserName(), user.getUserSurName());
                if (userOptional.isPresent()) {
                    User user1 = userOptional.get();
                    //eger hazirda kayitli var ekstra olusturmuyorum.
                    guests.add(user1);
                    continue;
                }
                System.out.println(5);
                User guest = new User();
                if (user.getEmail()!=null && userService.findByLogin(user.getEmail())==null) {
                    //eger kullanici dbye kayitli degil ancak email varsa role guest yaptim.
                    guest.setUserRoleType(userRoleTypeService.getUserRoleTypeByRoleName("GUEST"));
                }
                System.out.println(6);
                guest.setUserName(user.getUserName());
                guest.setUserSurName(user.getUserSurName());
                guest.setAddress(user.getAddress());
                guest.setPhoneNumber(user.getPhoneNumber());
                guest.setEmail(user.getEmail());
                guest.setAdult(user.isAdult());
                System.out.println(7);
                User savedUser = userRepository.save(guest);

                guests.add(savedUser);
            }

        }
        System.out.println(8);
        reservation.setGuestList(guests);
        reservation.setStartDate(reservationRequest.getCheck_in());
        reservation.setEndDate(reservationRequest.getCheck_out());
        reservation.setCreationTime(LocalDateTime.now());
        System.out.println(9);
        reservation.setCancellationFee((reservationRequest.getCancellationFee()>=0)?reservationRequest.getCancellationFee():0);
        System.out.println(10);
        reservation.setCountOfAdult(reservationRequest.getCountOfAdult());
        System.out.println(11);
        reservation.setCountOfChild(reservationRequest.getCountOfChild());
        System.out.println(12);
        reservation.setFilteredBedTypeList(reservationRequest.getFilteredBedTypeList());
        System.out.println(13);
        reservation.setFilteredDisabled(reservationRequest.getFilteredDisabled());
        System.out.println(14);
        reservation.setFilteredFacilitiesList(reservationRequest.getFilteredFacilitiesList());
        System.out.println(15);
        reservation.setFilteredFloor(reservationRequest.getFilteredFloor());
        System.out.println(16);
        reservation.setTotalPrice(reservationRequest.getTotalPrice());
        System.out.println(17);
        reservation.setFilteredSmoking(reservationRequest.getFilteredSmoking());
        System.out.println(18);
        reservation.setFilteredRoomType(reservationRequest.getFilteredRoomType());
        System.out.println(19);

        System.out.println(reservation);

        return reservation;

    }

    public ReservationResponse mapReservationToReservationResponse(Reservation savedReservation) {
        ReservationResponse reservationResponse = new ReservationResponse();

        reservationResponse.setReservationId(savedReservation.getReservationId());
        reservationResponse.setTotalPrice(savedReservation.getTotalPrice());
        reservationResponse.setRoomPrice(savedReservation.getRoom().getPrice());
        reservationResponse.setRoomAdultCapacity(savedReservation.getRoom().getCountOfAdult());
        reservationResponse.setRoomChildCapacity(savedReservation.getRoom().getCountOfChild());
        reservationResponse.setCancellationFee(savedReservation.getCancellationFee());
        reservationResponse.setCountOfAdult(savedReservation.getCountOfAdult());
        reservationResponse.setCountOfChild(savedReservation.getCountOfChild());
        reservationResponse.setFilteredBedTypeList(savedReservation.getFilteredBedTypeList());
        reservationResponse.setFilteredFacilitiesList(savedReservation.getFilteredFacilitiesList());
        reservationResponse.setFilteredRoomType(savedReservation.getFilteredRoomType());
        reservationResponse.setRoomNumber(savedReservation.getRoom().getRoomNumber());
        reservationResponse.setFilteredDisabled(savedReservation.isFilteredDisabled());
        reservationResponse.setFilteredSmoking(savedReservation.isFilteredSmoking());
        reservationResponse.setFilteredFloor(savedReservation.getFilteredFloor());
        reservationResponse.setStartDate(savedReservation.getStartDate());
        reservationResponse.setEndDate(savedReservation.getEndDate());
        reservationResponse.setCreationTime(savedReservation.getCreationTime());
        reservationResponse.setCheckedIn(savedReservation.isCheckedIn());
        reservationResponse.setCheckedOut(savedReservation.isCheckedOut());
        reservationResponse.setCheckedInDateTime(savedReservation.getCheckedInDateTime());
        reservationResponse.setCheckedOutDateTime(savedReservation.getCheckedOutDateTime());

        reservationResponse.setCanceled(savedReservation.isCanceled());
        System.out.println("reservationResponse.isCanceled() = " + reservationResponse.isCanceled());
        for (User guest:savedReservation.getGuestList()){
            if (guest.getEmail()!=null){
                reservationResponse.setGuestEmail(guest.getEmail());
            }
        }


        List<UserResponseDto> children = new ArrayList<>();
        List<UserResponseDto> adults = new ArrayList<>();
        for (UserResponseDto userResponseDto:userMapper.mapUserListToUserResponseDtoList(savedReservation.getGuestList())){
            if (userResponseDto.isAdult()){
                System.out.println("userResponseDto.isAdult() = " + userResponseDto.isAdult());
                adults.add(userResponseDto);
            }else {
                System.out.println("userResponseDto.isAdult() = " + userResponseDto.isAdult());
                children.add(userResponseDto);
            }

        }

        reservationResponse.setAdultList(adults);
        reservationResponse.setChildList(children);

        return reservationResponse;
    }
}
