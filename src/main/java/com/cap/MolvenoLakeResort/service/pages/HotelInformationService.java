package com.cap.MolvenoLakeResort.service.pages;

import com.cap.MolvenoLakeResort.model.pages.HotelInformation;

public interface HotelInformationService {
    HotelInformation getHotelInformation();

    String save(HotelInformation hotelInformation);
}
