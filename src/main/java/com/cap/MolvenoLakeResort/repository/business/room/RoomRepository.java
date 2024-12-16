package com.cap.MolvenoLakeResort.repository.business.room;

import com.cap.MolvenoLakeResort.model.business.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long>{
    Optional<Room> findByRoomNumber(String roomNumber);

    void deleteByRoomNumber(String roomNumber);
    List<Room> findByCleanedFalse();

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Room r WHERE LOWER(r.roomNumber) = LOWER(:roomNumber)")
    boolean existsByRoomNumberIgnoreCase(@Param("roomNumber") String roomNumber);
//    @Query("SELECT r FROM Room r WHERE r.roomId NOT IN (SELECT res.room.roomId " +
//            "FROM Reservation res WHERE res.check_in < :checkOut AND res.check_out > :checkIn)")
//    List<Room> findAvailableRooms(LocalDateTime checkIn, LocalDateTime checkOut);

    @Query("SELECT r FROM Room r WHERE r.roomId NOT IN (SELECT res.room.roomId " +
            "FROM Reservation res WHERE res.startDate < :checkOut AND res.endDate > :checkIn AND res.canceled = false)")
    List<Room> findAvailableRooms(@Param("checkIn") LocalDateTime checkIn, @Param("checkOut") LocalDateTime checkOut);

}
