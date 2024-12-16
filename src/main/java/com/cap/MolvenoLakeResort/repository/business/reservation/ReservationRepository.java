package com.cap.MolvenoLakeResort.repository.business.reservation;

import com.cap.MolvenoLakeResort.model.business.reservation.Reservation;
import com.cap.MolvenoLakeResort.model.business.room.Room;
import com.cap.MolvenoLakeResort.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    @Query("SELECT r FROM Reservation r JOIN r.guestList g WHERE g.email = :email")
    List<Reservation> findByGuestEmail(@Param("email") String email);


    @Query("SELECT r FROM Reservation r WHERE r.room.id = :roomId AND r.endDate > :checkOutDate")
    List<Reservation> findReservationsByRoomIdAndCheckOutDateAfter(@Param("roomId") Long roomId, @Param("checkOutDate") LocalDateTime checkOutDate);

    @Query("SELECT r FROM Reservation r WHERE r.room.roomNumber = :roomNumber AND " +
            "((r.startDate < :newCheckOutDate AND r.endDate > :oldCheckOutDate) OR " +
            "(r.startDate BETWEEN :oldCheckOutDate AND :newCheckOutDate) OR " +
            "(r.endDate BETWEEN :oldCheckOutDate AND :newCheckOutDate))")
    List<Reservation> findReservationsBetweenDates(
            @Param("roomNumber") String roomNumber,
            @Param("oldCheckOutDate") LocalDateTime oldCheckOutDate,
            @Param("newCheckOutDate") LocalDateTime newCheckOutDate);

    @Query("SELECT u FROM User u JOIN u.reservationList r WHERE r = :reservation")
    List<User> findUsersByReservation(@Param("reservation") Reservation reservation);

    @Query("SELECT r FROM Reservation r WHERE r.room = :room AND r.endDate > CURRENT_TIMESTAMP")
    List<Reservation> findActiveOrFutureReservationsByRoom(@Param("room") Room room);
}



