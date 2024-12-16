package com.cap.MolvenoLakeResort.service.business.room;

import com.cap.MolvenoLakeResort.model.business.room.Facilities;
import com.cap.MolvenoLakeResort.payload.request.room.FacilitiesRequestDto;
import com.cap.MolvenoLakeResort.payload.response.room.FacilitiesResponseDto;

import java.util.List;

public interface FacilitiesService {
    List<FacilitiesResponseDto> getAllFacilities();
    List<FacilitiesResponseDto> getAllFacilities(String lang);
    String deleteFacilitiesById(Long id);

    FacilitiesResponseDto  saveFacilities(FacilitiesRequestDto facilitiesRequestDto);

    String deleteFacilitiesByFacilitiesName(String facilitiesName);

    FacilitiesResponseDto editFacilitiesByFacilitiesName(FacilitiesRequestDto facilitiesRequestDto, String facilitiesName);

    List<Facilities> getFacilitiesListByFacilitiesNames(List<String> facilitiesList);

    boolean canDeleteFacilities(Long id);
}
