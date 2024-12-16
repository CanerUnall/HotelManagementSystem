package com.cap.MolvenoLakeResort.controller.pages;

import com.cap.MolvenoLakeResort.model.pages.HotelAmenities;
import com.cap.MolvenoLakeResort.payload.response.page.HotelAmenitiesResponse;
import com.cap.MolvenoLakeResort.service.pages.HotelAmenitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotelAmenities")
public class HotelAmenitiesController {

    private final HotelAmenitiesService hotelAmenitiesService;

    @Autowired
    public HotelAmenitiesController(HotelAmenitiesService hotelAmenitiesService) {
        this.hotelAmenitiesService = hotelAmenitiesService;
    }


    @GetMapping
    public ResponseEntity<List<HotelAmenities>> getAllAmenities() {

        return ResponseEntity.ok(hotelAmenitiesService.getAllAmenities());

    }

    @PostMapping
    public HotelAmenities saveHotelAmenities(@RequestBody HotelAmenities hotelAmenities) {

        return hotelAmenitiesService.saveHotelAmenities(hotelAmenities);
    }

    @DeleteMapping("/{hotelAmenitiesId}")
    public ResponseEntity<String> deleteHotelAmenities(@PathVariable("hotelAmenitiesId") Long hotelAmenitiesId) {

        return ResponseEntity.ok(hotelAmenitiesService.deleteHotelAmenities(hotelAmenitiesId));
    }

    @GetMapping("/home")
    public ResponseEntity<List<HotelAmenitiesResponse>> getAmenitiesForHome(@RequestParam String lang) {
        System.out.println("lang = " + lang);

        return ResponseEntity.ok(hotelAmenitiesService.getAmenitiesByLanguage(lang));
    }


}
