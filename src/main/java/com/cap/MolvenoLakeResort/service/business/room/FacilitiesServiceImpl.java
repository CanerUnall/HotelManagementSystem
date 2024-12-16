package com.cap.MolvenoLakeResort.service.business.room;

import com.cap.MolvenoLakeResort.model.business.room.Facilities;
import com.cap.MolvenoLakeResort.payload.mappers.FacilitiesMapper;
import com.cap.MolvenoLakeResort.payload.request.room.FacilitiesRequestDto;
import com.cap.MolvenoLakeResort.payload.response.room.FacilitiesResponseDto;
import com.cap.MolvenoLakeResort.repository.business.room.FacilitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacilitiesServiceImpl implements FacilitiesService {

    private final FacilitiesRepository facilitiesRepository;
    private final FacilitiesMapper facilitiesMapper;

    @Autowired
    public FacilitiesServiceImpl(FacilitiesRepository facilitiesRepository, FacilitiesMapper facilitiesMapper) {
        this.facilitiesRepository = facilitiesRepository;
        this.facilitiesMapper = facilitiesMapper;
    }

    @Override
    public List<FacilitiesResponseDto> getAllFacilities() {
        List<Facilities> facilitiesList = facilitiesRepository.findAll();
        List<FacilitiesResponseDto> facilitiesResponseDtos = facilitiesMapper.mapFacilitiesListToFacilitiesResponseDtoList(facilitiesList);
        return facilitiesResponseDtos;

    }

    @Override
    public List<FacilitiesResponseDto> getAllFacilities(String lang) {
        List<Facilities> facilitiesList = facilitiesRepository.findAll();
        List<FacilitiesResponseDto> facilitiesResponseDtos = facilitiesMapper.mapFacilitiesListToFacilitiesResponseDtoList(facilitiesList,lang);
        return facilitiesResponseDtos;

    }

    @Override
    public String deleteFacilitiesById(Long id) {
        if (facilitiesRepository.findById(id).isPresent()) {
            facilitiesRepository.deleteById(id);
            return " Facility deleted successfully!";
        } else {
            return "Facility Not Found!";
        }
    }

    @Override
    public FacilitiesResponseDto saveFacilities(FacilitiesRequestDto facilitiesRequestDto) {

        Facilities facilities = facilitiesMapper.mapFacilitiesRequestDtoToFacilities(facilitiesRequestDto);
        Facilities savedFacilities = facilitiesRepository.save(facilities);
        FacilitiesResponseDto facilitiesResponseDto = facilitiesMapper.mapFacilitiestoFacilitiesResponseDto(savedFacilities);

        return facilitiesResponseDto;


    }

    @Override
    public String deleteFacilitiesByFacilitiesName(String facilitiesName) {
        if (facilitiesRepository.findByFacilitiesName(facilitiesName).isPresent()) {
            facilitiesRepository.deleteById(facilitiesRepository.findByFacilitiesName(facilitiesName).get().getFacilitiesId());
            return "Facility deleted successfully";
        } else {
            return "Facility Not Found";
        }
    }

    @Override
    public FacilitiesResponseDto editFacilitiesByFacilitiesName(FacilitiesRequestDto facilitiesRequestDto, String facilitiesName) {
        Facilities facilities = facilitiesRepository.findByFacilitiesName(facilitiesName).get();
        Facilities facilitiesToRegister = facilitiesMapper.mapFacilitiesRequestDtoToFacilities(facilitiesRequestDto);

        facilitiesToRegister.setFacilitiesName(facilities.getFacilitiesName());
        Facilities savedFacilities = facilitiesRepository.save(facilitiesToRegister);

        return facilitiesMapper.mapFacilitiestoFacilitiesResponseDto(savedFacilities);

    }

    @Override
    public List<Facilities> getFacilitiesListByFacilitiesNames(List<String> facilitiesList) {
        List<Facilities> facilities = new ArrayList<>();
        for (String facilitiesName : facilitiesList) {
            if (facilitiesRepository.findByFacilitiesName(facilitiesName).isPresent()) {
                facilities.add(facilitiesRepository.findByFacilitiesName(facilitiesName).get());
            }
        }
        return facilities;
    }

    @Override
    public boolean canDeleteFacilities(Long facilitiesId) {
        Facilities facilities = facilitiesRepository.findById(facilitiesId).orElse(null);
        if (facilities != null && facilities.getRoomList().isEmpty()) {
            return true;
        }
        return false;
    }
}









