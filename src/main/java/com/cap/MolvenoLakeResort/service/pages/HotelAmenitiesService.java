package com.cap.MolvenoLakeResort.service.pages;

import com.cap.MolvenoLakeResort.model.pages.HotelAmenities;
import com.cap.MolvenoLakeResort.payload.response.page.HotelAmenitiesResponse;

import java.util.List;

public interface HotelAmenitiesService {
    List<HotelAmenities> getAllAmenities();

    HotelAmenities saveHotelAmenities(HotelAmenities hotelAmenities);

    String deleteHotelAmenities(Long hotelAmenitiesId);

    List<HotelAmenitiesResponse> getAmenitiesByLanguage(String lang);
}
