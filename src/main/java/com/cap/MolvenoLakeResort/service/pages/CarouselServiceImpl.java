package com.cap.MolvenoLakeResort.service.pages;

import com.cap.MolvenoLakeResort.model.media.Image;
import com.cap.MolvenoLakeResort.model.pages.Carousel;
import com.cap.MolvenoLakeResort.payload.request.media.ImageRequestDto;
import com.cap.MolvenoLakeResort.payload.response.page.CarouselResponse;
import com.cap.MolvenoLakeResort.repository.pages.CarouselRepository;
import com.cap.MolvenoLakeResort.service.media.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarouselServiceImpl implements CarouselService {
    private final ImageService imageService;
    private final CarouselRepository carouselRepository;

    @Autowired
    public CarouselServiceImpl(ImageService imageService, CarouselRepository carouselRepository) {
        this.imageService = imageService;
        this.carouselRepository = carouselRepository;
    }

    @Override
    public List<Carousel> getAllCarousels() {
        List<Carousel> carouselList = carouselRepository.findAll();
        System.out.println("carouselList.size() = " + carouselList.size());
        return carouselList;
    }

    @Override
    public Carousel saveCarousel(Carousel carousel, ImageRequestDto imageRequestDto) {
        if (imageRequestDto != null) {
            Image image = imageService.saveImage(imageRequestDto, "PAGE");
            carousel.setImage(image);
        }
        // Resim dosyası yüklenmediyse, mevcut resim koru
        System.out.println("islem tamam");
        return carouselRepository.save(carousel);
    }


    @Override
    public String deleteCarousel(Long carouselId) {
        carouselRepository.deleteById(carouselId);

        return "success";
    }

    @Override
    public List<CarouselResponse> getCarouselByIdAndLanguage(String lang) {
        List<Carousel> carouselList = carouselRepository.findAll();

        List<CarouselResponse> carouselResponseList = new ArrayList<>();

        for (Carousel carousel:carouselList){
            CarouselResponse response = new CarouselResponse();
            response.setCarouselId(carousel.getCarouselId());
            if ("zh".equalsIgnoreCase(lang)) {
                response.setTitle(carousel.getCarouselTitleZh());
                response.setDescription(carousel.getDescriptionZh());
            } else {
                response.setTitle(carousel.getCarouselTitle());
                response.setDescription(carousel.getDescription());
            }
            response.setImage(carousel.getImage());
            carouselResponseList.add(response);
        }

        return carouselResponseList;
    }

    @Override
    public Optional<Carousel> findById(Long carouselId) {
        return carouselRepository.findById(carouselId);
    }
}
