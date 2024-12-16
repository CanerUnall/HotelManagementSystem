package com.cap.MolvenoLakeResort.controller.pages;

import com.cap.MolvenoLakeResort.model.pages.Carousel;
import com.cap.MolvenoLakeResort.payload.request.media.ImageRequestDto;
import com.cap.MolvenoLakeResort.payload.response.page.CarouselResponse;
import com.cap.MolvenoLakeResort.service.pages.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("api/carousel")
public class CarouselController {

    private final CarouselService carouselService;

    @Autowired
    public CarouselController(CarouselService carouselService) {
        this.carouselService = carouselService;
    }

    @GetMapping
    public ResponseEntity<List<Carousel>> getAllCarousels() {

        return ResponseEntity.ok(carouselService.getAllCarousels());

    }

    @PostMapping
    public Carousel saveCarousel(@RequestParam("carouselId") Long carouselId,
                                       @RequestParam("carouselTitle") String carouselTitle,
                                       @RequestParam("carouselTitleZh") String carouselTitleZh,
                                       @RequestParam("description") String description,
                                       @RequestParam("descriptionZh") String descriptionZh,
                                       @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {

        Carousel carousel;

        // Var olan bir Carousel'i güncelliyoruz, mevcut kaydı alıyoruz
        if (carouselId != null) {
            carousel = carouselService.findById(carouselId).orElse(new Carousel());
        } else {
            carousel = new Carousel();
        }

        carousel.setCarouselId(carouselId);
        carousel.setCarouselTitle(carouselTitle);
        carousel.setDescription(description);
        carousel.setCarouselTitleZh(carouselTitleZh);
        carousel.setDescriptionZh(descriptionZh);

        ImageRequestDto imageRequestDto = null;

        // Eğer resim dosyası yüklenmişse, imageRequestDto oluştur
        if (imageFile != null && !imageFile.isEmpty()) {
            imageRequestDto = new ImageRequestDto();
            imageRequestDto.setName(imageFile.getOriginalFilename());
            try {
                imageRequestDto.setUrl(Base64.getEncoder().encodeToString(imageFile.getBytes()));
            } catch (IOException e) {
                throw new RuntimeException("Error reading image file", e);
            }
        } else if (carousel.getImage() == null) {
            // Yeni bir Carousel ve fotoğraf yüklenmemişse, varsayılan bir fotoğraf ata
            imageRequestDto = new ImageRequestDto();
            imageRequestDto.setName("img/rooms/Molvenolakeresortintheevening1.webp");
            imageRequestDto.setUrl("img/rooms/Molvenolakeresortintheevening1.webp"); // Default image URL ya da base64 string
        }

        // Resim dosyası yoksa imageRequestDto null olacak
        return carouselService.saveCarousel(carousel, imageRequestDto);
    }



    @DeleteMapping("/{carouselId}")
    public ResponseEntity<String> deleteHotelAmenities(@PathVariable("carouselId") Long carouselId) {

        return ResponseEntity.ok(carouselService.deleteCarousel(carouselId));
    }

    @GetMapping("/home")
    public ResponseEntity<List<CarouselResponse>> getCarouselsForHome(@RequestParam String lang) {
        System.out.println("lang = " + lang);

        return ResponseEntity.ok(carouselService.getCarouselByIdAndLanguage(lang));
    }


}
