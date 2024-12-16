package com.cap.MolvenoLakeResort.service.media;

import com.cap.MolvenoLakeResort.model.media.Image;
import com.cap.MolvenoLakeResort.payload.request.media.ImageRequestDto;
import com.cap.MolvenoLakeResort.repository.media.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final String roomUploadDir = "img/rooms/";
    private final String pageUploadDir = "img/pages/";
    private final String iconUploadDir = "img/icons/";

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image saveImage(ImageRequestDto imageRequestDto, String type) {
        if (imageRequestDto != null && imageRequestDto.getUrl() != null && !imageRequestDto.getUrl().isEmpty()) {
            String fileName = imageRequestDto.getName();
            String imageUrl;

            // Kontrollere göre imageUrl oluşturun
            if (type.equals("ROOM")) {
                imageUrl = "img/rooms/" + fileName;
            } else if (type.equals("PAGE")){
                imageUrl = "img/pages/" + fileName;
            } else {
                imageUrl = "img/icons/" + fileName;
            }

            // Check for existing image by imageName or imageUrl
            Optional<Image> existingImageOptByName = imageRepository.findByImageName(fileName);
            Optional<Image> existingImageOptByUrl = imageRepository.findByImageUrl(imageUrl);

            if (existingImageOptByName.isPresent() || existingImageOptByUrl.isPresent()) {
                return existingImageOptByUrl.orElseGet(existingImageOptByName::get);
            }

            byte[] imageData = Base64.getDecoder().decode(imageRequestDto.getUrl());
            Path destinationDirPath;

            if (type.equals("ROOM")) {
                destinationDirPath = Paths.get(roomUploadDir);
            } else if (type.equals("PAGE")){
                destinationDirPath = Paths.get(pageUploadDir);
            } else {
                destinationDirPath = Paths.get(iconUploadDir);
            }

            File destinationDir = destinationDirPath.toFile();
            if (!destinationDir.exists()) {
                destinationDir.mkdirs();
            }

            File destinationFile = new File(destinationDir, fileName);
            try (FileOutputStream fos = new FileOutputStream(destinationFile)) {
                fos.write(imageData);
            } catch (IOException e) {
                throw new RuntimeException("Fotoğraf yüklenirken bir hata oluştu: " + e.getMessage());
            }

            Image image = new Image();
            image.setType(type);
            image.setImageUrl(imageUrl);
            image.setImageName(fileName);

            return imageRepository.save(image);
        } else {
            throw new RuntimeException("Fotoğraf boş olamaz");
        }
    }

    @Override
    public Image saveImage2(ImageRequestDto imageRequestDto, String type) {
        if (imageRequestDto != null && imageRequestDto.getUrl() != null && !imageRequestDto.getUrl().isEmpty()) {
            String fileName = imageRequestDto.getName();
            Optional<Image> existingImageOpt = imageRepository.findByImageName(fileName);

            if (existingImageOpt.isPresent()) {
                return existingImageOpt.get();
            }

            Image image = new Image();
            image.setImageName(fileName);
            image.setType(type);

            switch (type) {
                case "ROOM":
                    image.setImageUrl("img/rooms/" + fileName);
                    break;
                case "PAGE":
                    image.setImageUrl("img/pages/" + fileName);
                    break;
                default:
                    image.setImageUrl("img/icons/" + fileName);
                    break;
            }

            // URL'yi doğrudan setlemek
            image.setImageUrl(imageRequestDto.getUrl());

            return imageRepository.save(image);
        } else {
            throw new RuntimeException("Fotoğraf URL'si boş olamaz");
        }
    }


}
