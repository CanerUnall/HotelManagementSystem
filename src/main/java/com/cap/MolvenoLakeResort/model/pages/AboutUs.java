package com.cap.MolvenoLakeResort.model.pages;

import com.cap.MolvenoLakeResort.model.media.Image;
import jakarta.persistence.*;

@Entity
public class AboutUs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aboutUsId;



    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String descriptionZh;

    @ManyToOne
    private Image image;




    public String getDescriptionZh() {
        return descriptionZh;
    }

    public void setDescriptionZh(String descriptionZh) {
        this.descriptionZh = descriptionZh;
    }

    public Long getAboutUsId() {
        return aboutUsId;
    }

    public void setAboutUsId(Long aboutUsId) {
        this.aboutUsId = aboutUsId;
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

    public AboutUs(Long aboutUsId,
                   String description, String descriptionZh, Image image) {
        this.aboutUsId = aboutUsId;

        this.description = description;
        this.descriptionZh = descriptionZh;
        this.image = image;
    }

    public AboutUs() {
    }
}
