package com.cap.MolvenoLakeResort.payload.mappers;

import com.cap.MolvenoLakeResort.model.business.room.Facilities;
import com.cap.MolvenoLakeResort.payload.request.room.FacilitiesRequestDto;
import com.cap.MolvenoLakeResort.payload.response.room.FacilitiesResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class FacilitiesMapper {


    public List<FacilitiesResponseDto> mapFacilitiesListToFacilitiesResponseDtoList(List<Facilities> facilitiesList) {

        List<FacilitiesResponseDto> facilitiesResponseDtos = new ArrayList<>();

        for (Facilities facilities : facilitiesList) {
            FacilitiesResponseDto facilitiesResponseDto = new FacilitiesResponseDto();
            facilitiesResponseDto.setFacilitiesId(facilities.getFacilitiesId());
            facilitiesResponseDto.setFacilitiesName(facilities.getFacilitiesName());
            facilitiesResponseDto.setFacilitiesNameZh(facilities.getFacilitiesNameZh());
            facilitiesResponseDtos.add(facilitiesResponseDto);
        }
        return facilitiesResponseDtos;

    }

    public List<FacilitiesResponseDto> mapFacilitiesListToFacilitiesResponseDtoList(List<Facilities> facilitiesList, String lang) {

        List<FacilitiesResponseDto> facilitiesResponseDtos = new ArrayList<>();

        for (Facilities facilities : facilitiesList) {
            FacilitiesResponseDto facilitiesResponseDto = new FacilitiesResponseDto();
            facilitiesResponseDto.setFacilitiesId(facilities.getFacilitiesId());
            if ("zh".equalsIgnoreCase(lang)) {

                facilitiesResponseDto.setFacilitiesName(facilities.getFacilitiesNameZh());

            } else {
                facilitiesResponseDto.setFacilitiesName(facilities.getFacilitiesName());
            }

            facilitiesResponseDtos.add(facilitiesResponseDto);
        }
        return facilitiesResponseDtos;

    }

    public Facilities mapFacilitiesRequestDtoToFacilities(FacilitiesRequestDto facilitiesRequestDto) {
        Facilities facilities = new Facilities();
        facilities.setFacilitiesId(facilitiesRequestDto.getFacilitiesId());
        facilities.setFacilitiesName(facilitiesRequestDto.getFacilitiesName());
        facilities.setFacilitiesNameZh(facilitiesRequestDto.getFacilitiesNameZh());
        return facilities;

    }

    public FacilitiesResponseDto mapFacilitiestoFacilitiesResponseDto(Facilities savedFacilities) {
        FacilitiesResponseDto facilitiesResponseDto = new FacilitiesResponseDto();
        facilitiesResponseDto.setFacilitiesId(savedFacilities.getFacilitiesId());
        facilitiesResponseDto.setFacilitiesName(savedFacilities.getFacilitiesName());
        facilitiesResponseDto.setFacilitiesNameZh(savedFacilities.getFacilitiesNameZh());
        return facilitiesResponseDto;
    }
}
