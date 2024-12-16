package com.cap.MolvenoLakeResort.controller.media;

import com.cap.MolvenoLakeResort.service.media.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }


//    @PostMapping
//    public ResponseEntity <ImageControllerResponseDto> save(@RequestBody ImageControllerRequestDto imageControllerRequestDto){
//        ImageControllerResponseDto imageControllerResponseDto = ImageService.save(imageControllerRequestDto);
//        return ResponseEntity.ok(imageControllerResponseDto);
//    }

}
