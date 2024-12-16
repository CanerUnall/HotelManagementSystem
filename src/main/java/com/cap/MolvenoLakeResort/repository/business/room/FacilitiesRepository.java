package com.cap.MolvenoLakeResort.repository.business.room;

import com.cap.MolvenoLakeResort.model.business.room.Facilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacilitiesRepository extends JpaRepository<Facilities,Long> {


    Optional<Facilities> findByFacilitiesName(String facilitiesName);

    void deleteByFacilitiesName(String facilitiesName);
}
