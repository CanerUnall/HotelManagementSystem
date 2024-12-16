package com.cap.MolvenoLakeResort.service.pages;

import com.cap.MolvenoLakeResort.model.pages.HotelInformation;
import com.cap.MolvenoLakeResort.repository.pages.HotelInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HotelInformationServiceImpl implements HotelInformationService{

    private final HotelInformationRepository hotelInformationRepository;

    @Autowired
    public HotelInformationServiceImpl(HotelInformationRepository hotelInformationRepository) {
        this.hotelInformationRepository = hotelInformationRepository;
    }
    @Override
    public HotelInformation getHotelInformation() {

        Optional<HotelInformation> hotelInformation = hotelInformationRepository.findById(1L);

        return hotelInformation.orElse(null);

    }
    @Override
    public String save(HotelInformation hotelInformation) {


        hotelInformationRepository.save(hotelInformation);
        return "success";

    }
}
