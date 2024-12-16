package com.cap.MolvenoLakeResort.repository.pages;

import com.cap.MolvenoLakeResort.model.pages.HotelAmenities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelAmenitiesRepository extends JpaRepository<HotelAmenities, Long> {
}
