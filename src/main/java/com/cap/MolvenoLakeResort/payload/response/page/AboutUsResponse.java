package com.cap.MolvenoLakeResort.payload.response.page;

import com.cap.MolvenoLakeResort.model.media.Image;

public class AboutUsResponse {

    private Long aboutUsId;
    private String description;
    private Image image;

    public Long getAboutUsId() {
        return aboutUsId;
    }

    public void setAboutUsId(Long aboutUsId) {
        this.aboutUsId = aboutUsId;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
