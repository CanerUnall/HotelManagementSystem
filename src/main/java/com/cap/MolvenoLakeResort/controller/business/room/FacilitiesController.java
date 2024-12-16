package com.cap.MolvenoLakeResort.controller.business.room;

import com.cap.MolvenoLakeResort.payload.request.room.FacilitiesRequestDto;
import com.cap.MolvenoLakeResort.payload.response.room.FacilitiesResponseDto;
import com.cap.MolvenoLakeResort.service.business.room.FacilitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/facilities")
public class FacilitiesController {
    private final FacilitiesService facilitiesService;

    @Autowired
    public FacilitiesController(FacilitiesService facilitiesService) {
        this.facilitiesService = facilitiesService;
    }


    @GetMapping("")//http://localhost:8080/api/reservation
    public ResponseEntity<List<FacilitiesResponseDto>> getAllFacilities() {
        List<FacilitiesResponseDto> facilitiesResponseDtos = facilitiesService.getAllFacilities();

        return new ResponseEntity<>(facilitiesResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/forBookNow")//http://localhost:8080/api/reservation
    public ResponseEntity<List<FacilitiesResponseDto>> getAllFacilities(@RequestParam String lang) {
        List<FacilitiesResponseDto> facilitiesResponseDtos = facilitiesService.getAllFacilities(lang);

        return new ResponseEntity<>(facilitiesResponseDtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<FacilitiesResponseDto> saveFacilitiesByFacilitiesName(@RequestBody FacilitiesRequestDto facilitiesRequestDto) {
        FacilitiesResponseDto facilitiesResponseDto = facilitiesService.saveFacilities(facilitiesRequestDto);
        return ResponseEntity.ok(facilitiesResponseDto);
    }

    @DeleteMapping("/{facilitiesName}")
//http:localhost:8080/facilities/deleteFacilitiesByFacilitiesName/
    public ResponseEntity<String> deleteFacilitiesByFacilitiesName(@PathVariable("facilitiesName") String facilitiesName) {
        String result = facilitiesService.deleteFacilitiesByFacilitiesName(facilitiesName);
        return ResponseEntity.ok(result);

    }

    @PutMapping("/editFacilitiesByFacilitiesName/{facilitiesName}")
    public ResponseEntity<FacilitiesResponseDto> editFacilitiesByFacilitiesName(@RequestBody FacilitiesRequestDto facilitiesRequestDto,
                                                                                @PathVariable("facilitiesName") String facilitiesName) {
        FacilitiesResponseDto facilitiesResponseDto = facilitiesService.editFacilitiesByFacilitiesName(facilitiesRequestDto, facilitiesName);

        return ResponseEntity.ok(facilitiesResponseDto);
    }

    @GetMapping("/can-delete/{id}")
    public ResponseEntity<Boolean> canDeleteFacilities(@PathVariable Long id) {
        boolean canDelete = facilitiesService.canDeleteFacilities(id);
        return ResponseEntity.ok(canDelete);
    }

}
