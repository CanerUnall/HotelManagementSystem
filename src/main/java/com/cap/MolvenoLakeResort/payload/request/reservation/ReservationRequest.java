package com.cap.MolvenoLakeResort.payload.request.reservation;

import com.cap.MolvenoLakeResort.payload.request.user.UserRequestDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationRequest {

    private long reservationId;
    private String roomNumber;
    private String filteredRoomType;
    private double totalPrice;
    private List<UserRequestDto> guestList= new ArrayList<>();
    private int countOfAdult;
    private int countOfChild;
    private Boolean filteredSmoking;
    private List<String> filteredFacilitiesList = new ArrayList<>();
    private List<String> filteredBedTypeList = new ArrayList<>();
    private String filteredFloor;
    private Boolean filteredDisabled;
    private double cancellationFee;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
    private LocalDateTime check_in;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
    private LocalDateTime check_out;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
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

    public String getFilteredRoomType() {
        return filteredRoomType;
    }

    public void setFilteredRoomType(String filteredRoomType) {
        this.filteredRoomType = filteredRoomType;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<UserRequestDto> getGuestList() {
        return guestList;
    }

    public void setGuestList(List<UserRequestDto> guestList) {
        this.guestList = guestList;
    }

    public int getCountOfAdult() {
        return countOfAdult;
    }

    public void setCountOfAdult(int countOfAdult) {
        this.countOfAdult = countOfAdult;
    }

    public int getCountOfChild() {
        return countOfChild;
    }

    public void setCountOfChild(int countOfChild) {
        this.countOfChild = countOfChild;
    }

    public Boolean getFilteredSmoking() {
        return filteredSmoking;
    }

    public void setFilteredSmoking(Boolean filteredSmoking) {
        this.filteredSmoking = filteredSmoking;
    }

    public List<String> getFilteredFacilitiesList() {
        return filteredFacilitiesList;
    }

    public void setFilteredFacilitiesList(List<String> filteredFacilitiesList) {
        this.filteredFacilitiesList = filteredFacilitiesList;
    }

    public List<String> getFilteredBedTypeList() {
        return filteredBedTypeList;
    }

    public void setFilteredBedTypeList(List<String> filteredBedTypeList) {
        this.filteredBedTypeList = filteredBedTypeList;
    }

    public String getFilteredFloor() {
        return filteredFloor;
    }

    public void setFilteredFloor(String filteredFloor) {
        this.filteredFloor = filteredFloor;
    }

    public Boolean getFilteredDisabled() {
        return filteredDisabled;
    }

    public void setFilteredDisabled(Boolean filteredDisabled) {
        this.filteredDisabled = filteredDisabled;
    }

    public double getCancellationFee() {
        return cancellationFee;
    }

    public void setCancellationFee(double cancellationFee) {
        this.cancellationFee = cancellationFee;
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
}
