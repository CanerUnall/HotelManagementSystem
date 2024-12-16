package com.cap.MolvenoLakeResort.service.pages;

import com.cap.MolvenoLakeResort.model.pages.AboutUs;
import com.cap.MolvenoLakeResort.payload.request.media.ImageRequestDto;
import com.cap.MolvenoLakeResort.payload.response.page.AboutUsResponse;

import java.util.List;
import java.util.Optional;

public interface AboutUsService {
    List<AboutUs> getAllAboutUs();

    AboutUs saveAboutUs(AboutUs aboutUs, ImageRequestDto imageRequestDto);

    String deleteAboutUs(Long aboutUsId);

    AboutUsResponse getAboutUsByIdAndLanguage(Long id, String lang);

    Optional<AboutUs> findById(Long aboutUsId);
}
