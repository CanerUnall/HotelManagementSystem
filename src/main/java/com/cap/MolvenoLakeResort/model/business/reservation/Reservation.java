package com.cap.MolvenoLakeResort.model.business.reservation;

import com.cap.MolvenoLakeResort.model.business.room.Room;
import com.cap.MolvenoLakeResort.model.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @ManyToOne
    private Room room;

    @JsonIgnoreProperties("reservationList")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_reservation",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> guestList = new ArrayList<>();

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
    private LocalDateTime endDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
    private LocalDateTime creationTime;

    private int countOfAdult;
    private int countOfChild;
    private boolean filteredSmoking;
    private List<String> filteredFacilitiesList;
    private List<String> filteredBedTypeList;
    private String filteredFloor;
    private boolean filteredDisabled;
    private String filteredRoomType;
    private double totalPrice;
    private double cancellationFee;
    private boolean canceled;
    private boolean checkedIn;
    private boolean checkedOut;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
    private LocalDateTime checkedInDateTime;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
    private LocalDateTime checkedOutDateTime;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(reservationId, that.reservationId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(reservationId);
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

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public List<User> getGuestList() {
        return guestList;
    }

    public void setGuestList(List<User> guestList) {
        this.guestList = guestList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
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

    public boolean isFilteredSmoking() {
        return filteredSmoking;
    }

    public void setFilteredSmoking(boolean filteredSmoking) {
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

    public boolean isFilteredDisabled() {
        return filteredDisabled;
    }

    public void setFilteredDisabled(boolean filteredDisabled) {
        this.filteredDisabled = filteredDisabled;
    }

    public String getFilteredRoomType() {
        return filteredRoomType;
    }

    public void setFilteredRoomType(String filteredRoomType) {
        this.filteredRoomType = filteredRoomType;
    }

    public double getCancellationFee() {
        return cancellationFee;
    }

    public void setCancellationFee(double cancellationFee) {
        this.cancellationFee = cancellationFee;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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



    public Reservation() {
        creationTime =LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", room=" + room +
                ", check_in=" + startDate +
                ", check_out=" + endDate +
                ", transactionDateTime=" + creationTime +
                ", countOfAdult=" + countOfAdult +
                ", countOfChild=" + countOfChild +
                ", filteredSmoking=" + filteredSmoking +
                ", filteredFloor='" + filteredFloor + '\'' +
                ", filteredDisabled=" + filteredDisabled +
                ", filteredRoomType='" + filteredRoomType + '\'' +
                ", totalPrice=" + totalPrice +
                ", cancellationFee=" + cancellationFee +
                '}';
    }
}
