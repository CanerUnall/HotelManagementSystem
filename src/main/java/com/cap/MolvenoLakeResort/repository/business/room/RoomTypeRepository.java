package com.cap.MolvenoLakeResort.repository.business.room;

import com.cap.MolvenoLakeResort.model.business.room.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType,Long> {

    Optional<RoomType> findByRoomTypeName(String roomTypeName);
    void deleteByRoomTypeName(String roomTypeName);
}
