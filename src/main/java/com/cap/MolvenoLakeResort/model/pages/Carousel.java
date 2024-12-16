package com.cap.MolvenoLakeResort.model.pages;

import com.cap.MolvenoLakeResort.model.media.Image;
import jakarta.persistence.*;

@Entity
public class Carousel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carouselId;
    private String carouselTitle;
    private String description;

    private String carouselTitleZh;
    private String descriptionZh;

    @ManyToOne
    private Image image;

    public String getCarouselTitleZh() {
        return carouselTitleZh;
    }

    public void setCarouselTitleZh(String carouselTitleZh) {
        this.carouselTitleZh = carouselTitleZh;
    }

    public String getDescriptionZh() {
        return descriptionZh;
    }

    public void setDescriptionZh(String descriptionZh) {
        this.descriptionZh = descriptionZh;
    }

    public Long getCarouselId() {
        return carouselId;
    }

    public void setCarouselId(Long carouselId) {
        this.carouselId = carouselId;
    }

    public String getCarouselTitle() {
        return carouselTitle;
    }

    public void setCarouselTitle(String carouselTitle) {
        this.carouselTitle = carouselTitle;
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

    public Carousel(Long carouselId, String carouselTitle, String description,
                    String carouselTitleZh, String descriptionZh, Image image) {
        this.carouselId = carouselId;
        this.carouselTitle = carouselTitle;
        this.description = description;
        this.carouselTitleZh = carouselTitleZh;
        this.descriptionZh = descriptionZh;
        this.image = image;
    }

    public Carousel() {
    }
}
