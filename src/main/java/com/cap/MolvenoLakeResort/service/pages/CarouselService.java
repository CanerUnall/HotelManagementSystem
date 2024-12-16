package com.cap.MolvenoLakeResort.service.pages;

import com.cap.MolvenoLakeResort.model.pages.Carousel;
import com.cap.MolvenoLakeResort.payload.request.media.ImageRequestDto;
import com.cap.MolvenoLakeResort.payload.response.page.CarouselResponse;

import java.util.List;
import java.util.Optional;

public interface CarouselService {
    List<Carousel> getAllCarousels();

    Carousel saveCarousel(Carousel carousel, ImageRequestDto imageRequestDto);

    String deleteCarousel(Long carouselId);

    List<CarouselResponse> getCarouselByIdAndLanguage(String lang);

    Optional<Carousel> findById(Long carouselId);
}
