package com.cap.MolvenoLakeResort.controller.pages;

import com.cap.MolvenoLakeResort.model.pages.AboutUs;
import com.cap.MolvenoLakeResort.payload.request.media.ImageRequestDto;
import com.cap.MolvenoLakeResort.payload.response.page.AboutUsResponse;
import com.cap.MolvenoLakeResort.service.pages.AboutUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("api/aboutUs")
public class AboutUsController {

    private final AboutUsService aboutUsService;

    @Autowired
    public AboutUsController(AboutUsService aboutUsService) {
        this.aboutUsService = aboutUsService;
    }

    @GetMapping
    public ResponseEntity<List<AboutUs>> getAllAboutUs() {

        return ResponseEntity.ok(aboutUsService.getAllAboutUs());

    }

    @PostMapping
    public AboutUs saveAboutUs(@RequestParam("aboutUsId") Long aboutUsId,
                               @RequestParam("description") String description,
                               @RequestParam("descriptionZh") String descriptionZh,
                               @RequestParam(value = "image", required = false) MultipartFile imageFile) {

        AboutUs aboutUs;

        // Var olan bir AboutUs kaydını güncelliyoruz, mevcut kaydı alıyoruz
        if (aboutUsId != null) {
            aboutUs = aboutUsService.findById(aboutUsId).orElse(new AboutUs());
        } else {
            aboutUs = new AboutUs(); // Yeni bir AboutUs oluşturuluyor
        }

        aboutUs.setAboutUsId(aboutUsId);

        aboutUs.setDescription(description);
        aboutUs.setDescriptionZh(descriptionZh);

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
        } else if (aboutUs.getImage() == null) {
            // Yeni bir AboutUs ve fotoğraf yüklenmemişse, varsayılan bir fotoğraf ata
            imageRequestDto = new ImageRequestDto();
            imageRequestDto.setName("img/pages/CompanyLogo.jpg");
            imageRequestDto.setUrl("img/pages/CompanyLogo.jpg"); // Default image URL ya da base64 string
        }

        return aboutUsService.saveAboutUs(aboutUs, imageRequestDto);
    }


    @DeleteMapping("/{aboutUsId}")
    public ResponseEntity<String> deleteHotelAmenities(@PathVariable("aboutUsId") Long aboutUsId) {

        return ResponseEntity.ok(aboutUsService.deleteAboutUs(aboutUsId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AboutUsResponse> getAboutUsById(@PathVariable Long id, @RequestParam String lang) {
        System.out.println("lang = " + lang);
        AboutUsResponse aboutUsResponse = aboutUsService.getAboutUsByIdAndLanguage(id, lang);
        return ResponseEntity.ok(aboutUsResponse);
    }
}
