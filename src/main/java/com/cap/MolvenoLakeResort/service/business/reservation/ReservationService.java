package com.cap.MolvenoLakeResort.service.business.reservation;

import com.cap.MolvenoLakeResort.payload.request.reservation.ReservationRequest;
import com.cap.MolvenoLakeResort.payload.request.reservation.ReservationRequestDto;
import com.cap.MolvenoLakeResort.payload.response.reservation.ReservationResponse;
import com.cap.MolvenoLakeResort.payload.response.user.UserResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {
    List<ReservationResponse> getAllReservations();

    String deleteReservationById(Long id);

    String editReservation(ReservationRequestDto reservationRequestDto);

    String create(ReservationRequest reservationRequest);

    ReservationResponse getReservationById(Long id);

    List<ReservationResponse> getReservationListByEmail(String email);


    String cancelReservationById(Long reservationId, boolean canceled, int cancellationFeePercentage);


    boolean isRoomAvailable(String  roomNumber, LocalDateTime oldCheckOutDate, LocalDateTime newCheckOutDate);

    List<UserResponseDto> getAllGuestsByReservationId(Long reservationId);

    boolean checkInReservation(Long reservationId);

    boolean checkOutReservation(Long reservationId);
}
