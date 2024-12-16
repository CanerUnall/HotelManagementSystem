package com.cap.MolvenoLakeResort.service.pages;

import com.cap.MolvenoLakeResort.model.pages.HotelAmenities;
import com.cap.MolvenoLakeResort.payload.response.page.HotelAmenitiesResponse;
import com.cap.MolvenoLakeResort.repository.pages.HotelAmenitiesRepository;
import com.cap.MolvenoLakeResort.service.media.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelAmenitiesServiceImpl implements HotelAmenitiesService {
    private final ImageService imageService;
    private final HotelAmenitiesRepository hotelAmenitiesRepository;

    @Autowired
    public HotelAmenitiesServiceImpl(ImageService imageService, HotelAmenitiesRepository hotelAmenitiesRepository) {
        this.imageService = imageService;
        this.hotelAmenitiesRepository = hotelAmenitiesRepository;
    }


    @Override
    public List<HotelAmenities> getAllAmenities() {

        return hotelAmenitiesRepository.findAll();

    }

    @Override
    public HotelAmenities saveHotelAmenities(HotelAmenities hotelAmenities) {
        //Image image = imageService.saveImage(imageRequestDto, "ICON");

        return hotelAmenitiesRepository.save(hotelAmenities);
    }

    @Override
    public String deleteHotelAmenities(Long hotelAmenitiesId) {

        hotelAmenitiesRepository.deleteById(hotelAmenitiesId);

        return "success";
    }

    @Override
    public List<HotelAmenitiesResponse> getAmenitiesByLanguage(String lang) {

        List<HotelAmenities> amenitiesList = hotelAmenitiesRepository.findAll();

        List<HotelAmenitiesResponse> amenitiesResponseList = new ArrayList<>();
        for (HotelAmenities hotelAmenities:amenitiesList){
            HotelAmenitiesResponse response = new HotelAmenitiesResponse();
            response.setHotelAmenitiesId(hotelAmenities.getHotelAmenitiesId());
            if ("zh".equalsIgnoreCase(lang)) {
                response.setHotelAmenitiesContent(hotelAmenities.getHotelAmenitiesContentZh());
                response.setHotelAmenitiesName(hotelAmenities.getHotelAmenitiesNameZh());
            } else {
                response.setHotelAmenitiesName(hotelAmenities.getHotelAmenitiesName());
                response.setHotelAmenitiesContent(hotelAmenities.getHotelAmenitiesContent());
            }

            amenitiesResponseList.add(response);
        }

        return amenitiesResponseList;
    }
}
