package com.cap.MolvenoLakeResort.service.media;

import com.cap.MolvenoLakeResort.model.media.Image;
import com.cap.MolvenoLakeResort.payload.request.media.ImageRequestDto;

public interface ImageService {
    Image saveImage(ImageRequestDto imageRequestDto, String type);

    Image saveImage2(ImageRequestDto imageRequestDto, String type);
}
