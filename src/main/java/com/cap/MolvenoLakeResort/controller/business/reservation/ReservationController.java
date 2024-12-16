package com.cap.MolvenoLakeResort.controller.business.reservation;

import com.cap.MolvenoLakeResort.config.SecurityUtil;
import com.cap.MolvenoLakeResort.payload.request.reservation.ReservationRequest;
import com.cap.MolvenoLakeResort.payload.request.reservation.ReservationRequestDto;
import com.cap.MolvenoLakeResort.payload.request.user.UserRequestDto;
import com.cap.MolvenoLakeResort.payload.response.reservation.ReservationResponse;
import com.cap.MolvenoLakeResort.payload.response.user.UserResponseDto;
import com.cap.MolvenoLakeResort.service.business.reservation.ReservationService;
import com.cap.MolvenoLakeResort.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
private final UserService userService;
    private final ReservationService reservationService;
    private final SecurityUtil securityUtil;

    @Autowired
    public ReservationController(UserService userService, ReservationService reservationService, SecurityUtil securityUtil) {
        this.userService = userService;
        this.reservationService = reservationService;
        this.securityUtil = securityUtil;
    }


    @GetMapping//http://localhost:8080/api/reservation
    public ResponseEntity<List<ReservationResponse>> getAllReservations() {

        List<ReservationResponse> reservationResponseList = reservationService.getAllReservations();


        return new ResponseEntity<>(reservationResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> getReservationById(@PathVariable Long id) {
        ReservationResponse reservationResponse = reservationService.getReservationById(id);
//        return reservation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        return ResponseEntity.ok(reservationResponse);
    }

    @DeleteMapping(value = "/deleteById/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> deleteReservationById(@PathVariable("id") Long id) {
        System.out.println("Inside deleteUserById");
        String result = reservationService.deleteReservationById(id);

        return ResponseEntity.ok("User with id: " + id + " is deleted");
    }


//    @Autowired
//    private EmailService emailService;
//
//    @PostMapping("/reserve")
//    public String reserveRoom(@RequestBody ReservationRequest request) {
//        // Rezervasyon işlemleri burada yapılır
//        // ...
//
//        // Email gönderimi
//        Map<String, Object> model = new HashMap<>();
//        model.put("name", request.getName());
//        model.put("checkin_date", request.getCheckinDate());
//        model.put("checkout_date", request.getCheckoutDate());
//
//        try {
//            emailService.sendHtmlMessage(request.getEmail(), "Reservation Confirmation", model);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            return "Error sending email";
//        }
//
//        return "Reservation successful";
//    }

    @PostMapping(value = "bookNow", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> bookNow(@RequestBody ReservationRequest reservationRequest) {
        System.out.println("reservationRequest.getFilteredFacilitiesList() = " + reservationRequest.getFilteredFacilitiesList());
        System.out.println("reservationRequest.getCancellationFee() = " + reservationRequest.getCancellationFee());
        System.out.println("reservationRequest.getReservationId() = " + reservationRequest.getReservationId());
        System.out.println("reservationRequest.getCheck_in() = " + reservationRequest.getCheck_in());
        System.out.println("reservationRequest.getCheck_out() = " + reservationRequest.getCheck_out());
        System.out.println("reservationRequest.getCountOfAdult() = " + reservationRequest.getCountOfAdult());
        System.out.println("reservationRequest.getCountOfChild() = " + reservationRequest.getCountOfChild());
        System.out.println("reservationRequest.getFilteredDisabled() = " + reservationRequest.getFilteredDisabled());
        System.out.println("reservationRequest.getFilteredBedTypeList() = " + reservationRequest.getFilteredBedTypeList());
        System.out.println("reservationRequest.getTransactionDateTime() = " + reservationRequest.getTransactionDateTime());

        for (UserRequestDto userRequestDto :reservationRequest.getGuestList()){
            System.out.println(userRequestDto);
        }


        return ResponseEntity.ok(reservationService.create(reservationRequest));
    }


    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> edit(@RequestBody ReservationRequestDto reservationRequestDto) {
        System.out.println("Inside edit reservation");
        return ResponseEntity.ok(reservationService.editReservation(reservationRequestDto));
    }

    @GetMapping("/myReservation")
    public ResponseEntity<List<ReservationResponse>> getMyReservation(){
        String email =securityUtil.getCurrentUserEmail();
        System.out.println(email);
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        return ResponseEntity.ok(reservationService.getReservationListByEmail(email));
    }


    @GetMapping("/cancelById/{reservationId}/{canceled}/{cancellationFeePercentage}")
    public ResponseEntity<String> cancelReservationById(@PathVariable("reservationId") Long reservationId,
                                                        @PathVariable("canceled") boolean canceled,
                                                        @PathVariable("cancellationFeePercentage") int cancellationFeePercentage) {
        System.out.println("in the controller");
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Canceled: " + canceled);
        return ResponseEntity.ok(reservationService.cancelReservationById(reservationId, canceled,cancellationFeePercentage));
    }

    @GetMapping("/{reservationId}/guests")
    public List<UserResponseDto> getGuestNames(@PathVariable Long reservationId) {

            return reservationService.getAllGuestsByReservationId(reservationId);

    }


    @GetMapping("/availability")
    public ResponseEntity<Boolean> checkRoomAvailability(
            @RequestParam String roomNumber,
            @RequestParam LocalDateTime oldCheckOutDate,
            @RequestParam LocalDateTime newCheckOutDate) {
        System.out.println("roomNumber = " + roomNumber);
        System.out.println("oldCheckOutDate = " + oldCheckOutDate);
        System.out.println("newCheckOutDate = " + newCheckOutDate);
        boolean isAvailable = reservationService.isRoomAvailable(roomNumber, oldCheckOutDate.plusMinutes(1), newCheckOutDate);
        return ResponseEntity.ok(isAvailable);
    }

    @PostMapping("/checkIn/{reservationId}")
    public ResponseEntity<String> checkInReservation(@PathVariable Long reservationId) {
        boolean success = reservationService.checkInReservation(reservationId);
        if (success) {
            return ResponseEntity.ok("Reservation checked in successfully.");
        } else {
            return ResponseEntity.status(400).body("Failed to check in reservation.");
        }
    }

    @PostMapping("/checkOut/{reservationId}")
    public ResponseEntity<String> checkOutReservation(@PathVariable Long reservationId) {
        boolean success = reservationService.checkOutReservation(reservationId);
        if (success) {
            return ResponseEntity.ok("Reservation checked out successfully.");
        } else {
            return ResponseEntity.status(400).body("Failed to check out reservation.");
        }
    }

    @PostMapping("/checkUserInfo")
    public ResponseEntity<String> checkUserInfo(@RequestBody UserRequestDto userInfo) {
        UserResponseDto existingUser = userService.getUserByEmail(userInfo.getEmail());
        if (existingUser != null) {
            boolean isDifferent = !existingUser.getUserName().equals(userInfo.getUserName()) ||
                    !existingUser.getUserSurName().equals(userInfo.getUserSurName()) ||
                    !existingUser.getPhoneNumber().equals(userInfo.getPhoneNumber()) ||
                    !existingUser.getAddress().equals(userInfo.getAddress());
            if (isDifferent) {
                return ResponseEntity.badRequest().body("Entered information does not match existing user information.");
            }
        }
        return ResponseEntity.ok("Information is valid.");
    }


}
