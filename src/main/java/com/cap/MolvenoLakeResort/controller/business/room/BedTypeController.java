package com.cap.MolvenoLakeResort.controller.business.room;

import com.cap.MolvenoLakeResort.payload.request.room.BedTypeRequestDto;
import com.cap.MolvenoLakeResort.payload.response.room.BedTypeResponseDto;
import com.cap.MolvenoLakeResort.service.business.room.BedTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bedType")
public class BedTypeController {

    private final BedTypeService bedTypeService;

    @Autowired
    public BedTypeController(BedTypeService bedTypeService) {
        this.bedTypeService = bedTypeService;
    }

    @GetMapping()
    public ResponseEntity<List<BedTypeResponseDto>> getAllBedType() {

        List<BedTypeResponseDto> bedTypeResponseDto = bedTypeService.getAllBedType();

        return new ResponseEntity<>(bedTypeResponseDto, HttpStatus.OK);
    }

    @GetMapping("/forBookNow")
    public ResponseEntity<List<BedTypeResponseDto>> getAllBedType(@RequestParam String lang) {

        List<BedTypeResponseDto> bedTypeResponseDto = bedTypeService.getAllBedType(lang);

        return new ResponseEntity<>(bedTypeResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{bedTypeId}")
    //http://localhost:8080/bedType/deleteBedTypeByBedTypeName/
    public ResponseEntity<String> deleteBedTypeByBedTypeName(@PathVariable("bedTypeId") Long bedTypeId) {
        String result = bedTypeService.deleteBedTypeById(bedTypeId);
        return ResponseEntity.ok(result);


    }

    @PostMapping()//http://localhost:8080/api/bedType/
    public ResponseEntity<BedTypeResponseDto> saveBedTypeByBedTypeName(@RequestBody BedTypeRequestDto bedTypeRequestDto) {
        BedTypeResponseDto bedTypeResponseDto = bedTypeService.saveBedTypeByBedTypeName(bedTypeRequestDto);

        return ResponseEntity.ok(bedTypeResponseDto);
    }

    @PutMapping("editBedTypeByBedTypeName/{bedTypeName}")
    public ResponseEntity<BedTypeResponseDto> editBedTypeByBedTypeName(@RequestBody BedTypeRequestDto bedTypeRequestDto, @PathVariable("bedTypeName") String bedTypeName) {
        BedTypeResponseDto bedTypeResponseDto = bedTypeService.editBedTypeByBedTypeName(bedTypeRequestDto, bedTypeName);
        return ResponseEntity.ok(bedTypeResponseDto);
    }

    @GetMapping("/can-delete/{bedTypeId}")
    public ResponseEntity<Boolean> canDeleteBedType(@PathVariable Long bedTypeId) {
        boolean canDelete = bedTypeService.canDeleteBedType(bedTypeId);
        return ResponseEntity.ok(canDelete);
    }
}


