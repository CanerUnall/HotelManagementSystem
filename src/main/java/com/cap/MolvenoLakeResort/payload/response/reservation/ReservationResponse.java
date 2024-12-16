package com.cap.MolvenoLakeResort.payload.response.reservation;

import com.cap.MolvenoLakeResort.payload.response.user.UserResponseDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationResponse {

    private long reservationId;
    private String roomNumber;
    private int roomChildCapacity;
    private int roomAdultCapacity;
    private String filteredRoomType;
    private double roomPrice;
    private double totalPrice;
    private List<UserResponseDto> adultList;
    private List<UserResponseDto> childList;
    private int countOfAdult;
    private int countOfChild;
    private Boolean filteredSmoking;
    private List<String> filteredFacilitiesList;
    private List<String> filteredBedTypeList;
    private String filteredFloor;
    private Boolean filteredDisabled;
    private double cancellationFee;
    private String guestEmail;
    private boolean canceled;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
    private LocalDateTime endDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
    private LocalDateTime creationTime;
    private boolean checkedIn;
    private boolean checkedOut;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
    private LocalDateTime checkedInDateTime;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
    private LocalDateTime checkedOutDateTime;
    public boolean isCanceled() {
        return canceled;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public LocalDateTime getCheckedInDateTime() {
        return checkedInDateTime;
    }

    public void setCheckedInDateTime(LocalDateTime checkedInDateTime) {
        this.checkedInDateTime = checkedInDateTime;
    }

    public LocalDateTime getCheckedOutDateTime() {
        return checkedOutDateTime;
    }

    public void setCheckedOutDateTime(LocalDateTime checkedOutDateTime) {
        this.checkedOutDateTime = checkedOutDateTime;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
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

    public List<UserResponseDto> getAdultList() {
        return adultList;
    }

    public void setAdultList(List<UserResponseDto> adultList) {
        this.adultList = adultList;
    }

    public List<UserResponseDto> getChildList() {
        return childList;
    }

    public void setChildList(List<UserResponseDto> childList) {
        this.childList = childList;
    }

    public Boolean getFilteredSmoking() {
        return filteredSmoking;
    }

    public Boolean getFilteredDisabled() {
        return filteredDisabled;
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

    public Boolean isFilteredSmoking() {
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

    public Boolean isFilteredDisabled() {
        return filteredDisabled;
    }

    public void setFilteredDisabled(Boolean filteredDisabled) {
        this.filteredDisabled = filteredDisabled;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public double getCancellationFee() {
        return cancellationFee;
    }

    public void setCancellationFee(double cancellationFee) {
        this.cancellationFee = cancellationFee;
    }

    public int getRoomChildCapacity() {
        return roomChildCapacity;
    }

    public void setRoomChildCapacity(int roomChildCapacity) {
        this.roomChildCapacity = roomChildCapacity;
    }

    public int getRoomAdultCapacity() {
        return roomAdultCapacity;
    }

    public void setRoomAdultCapacity(int roomAdultCapacity) {
        this.roomAdultCapacity = roomAdultCapacity;
    }
}
