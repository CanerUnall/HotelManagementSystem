package com.cap.MolvenoLakeResort.service.pages;

import com.cap.MolvenoLakeResort.model.media.Image;
import com.cap.MolvenoLakeResort.model.pages.AboutUs;
import com.cap.MolvenoLakeResort.payload.request.media.ImageRequestDto;
import com.cap.MolvenoLakeResort.payload.response.page.AboutUsResponse;
import com.cap.MolvenoLakeResort.repository.pages.AboutUsRepository;
import com.cap.MolvenoLakeResort.service.media.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AboutUsServiceImpl implements AboutUsService {
    private final ImageService imageService;
    private final AboutUsRepository aboutUsRepository;

    @Autowired
    public AboutUsServiceImpl(ImageService imageService, AboutUsRepository aboutUsRepository) {
        this.imageService = imageService;
        this.aboutUsRepository = aboutUsRepository;
    }

    @Override
    public List<AboutUs> getAllAboutUs() {
        return aboutUsRepository.findAll();

    }

    @Override
    public AboutUs saveAboutUs(AboutUs aboutUs, ImageRequestDto imageRequestDto) {
        if (imageRequestDto != null) {
            Image image = imageService.saveImage(imageRequestDto, "PAGE");
            aboutUs.setImage(image);
        }
        // Resim dosyası yüklenmediyse, mevcut resim korunur veya default atanmış olur
        return aboutUsRepository.save(aboutUs);
    }


    @Override
    public String deleteAboutUs(Long aboutUsId) {
        aboutUsRepository.deleteById(aboutUsId);
        return "success";
    }
    @Override
    public AboutUsResponse getAboutUsByIdAndLanguage(Long id, String lang) {
        AboutUs aboutUs = aboutUsRepository.findById(id).orElse(null);
        if (aboutUs == null) {
            return null; // Or throw an exception
        }

        AboutUsResponse response = new AboutUsResponse();
        response.setAboutUsId(aboutUs.getAboutUsId());
        if ("zh".equalsIgnoreCase(lang)) {
            response.setDescription(aboutUs.getDescriptionZh());
        } else {
            response.setDescription(aboutUs.getDescription());
        }
        response.setImage(aboutUs.getImage());
        return response;
    }

    @Override
    public Optional<AboutUs> findById(Long aboutUsId) {
        return aboutUsRepository.findById(aboutUsId);
    }
}
