package com.cap.MolvenoLakeResort.controller.pages;

import com.cap.MolvenoLakeResort.model.pages.HotelInformation;
import com.cap.MolvenoLakeResort.service.pages.HotelInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hotelInformation")
public class HotelInformationController {

    private final HotelInformationService hotelInformationService;

    @Autowired
    public HotelInformationController(HotelInformationService hotelInformationService) {
        this.hotelInformationService = hotelInformationService;
    }


    @GetMapping
    public ResponseEntity<HotelInformation> getHotelInformation(){
        return ResponseEntity.ok(hotelInformationService.getHotelInformation());

    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody HotelInformation hotelInformation){

        return ResponseEntity.ok(hotelInformationService.save(hotelInformation));


    }





}
