package com.cap.MolvenoLakeResort.payload.response.page;

import com.cap.MolvenoLakeResort.model.media.Image;

public class CarouselResponse {
    private Long carouselId;
    private String title;
    private String description;
    private Image image;

    public Long getCarouselId() {
        return carouselId;
    }

    public void setCarouselId(Long carouselId) {
        this.carouselId = carouselId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
