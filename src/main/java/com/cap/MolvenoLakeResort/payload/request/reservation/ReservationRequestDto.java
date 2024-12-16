package com.cap.MolvenoLakeResort.payload.request.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class ReservationRequestDto {

    private long reservationId;
    private String roomNumber;
    private String guestEmail;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
    private LocalDateTime check_in;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
    private LocalDateTime check_out;
    private LocalDateTime transactionDateTime;

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public LocalDateTime getCheck_in() {
        return check_in;
    }

    public void setCheck_in(LocalDateTime check_in) {
        this.check_in = check_in;
    }

    public LocalDateTime getCheck_out() {
        return check_out;
    }

    public void setCheck_out(LocalDateTime check_out) {
        this.check_out = check_out;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public ReservationRequestDto() {
//        transactionDateTime= LocalDateTime.now();
    }
}
