package com.cap.MolvenoLakeResort.model.media;

import com.cap.MolvenoLakeResort.model.business.room.Room;
import com.cap.MolvenoLakeResort.model.pages.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageID;

    private String imageName;

    @Column(unique = true)
    private String imageUrl;

    private String type;

    @JsonIgnore
    @JsonIgnoreProperties("imageList")
    @ManyToMany(mappedBy = "imageList", cascade = CascadeType.MERGE)
    private List<Page> pageList = new ArrayList<>();

    @JsonIgnore
    @JsonIgnoreProperties("imageList")
    @ManyToMany(mappedBy = "imageList", cascade = CascadeType.MERGE)
    private List<Room> roomList = new ArrayList<>();

    public Image(String url) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(imageID, image.imageID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(imageID);
    }

    public Long getImageID() {
        return imageID;
    }

    public void setImageID(Long imageID) {
        this.imageID = imageID;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Page> getPageList() {
        return pageList;
    }

    public void setPageList(List<Page> pageList) {
        this.pageList = pageList;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public Image(Long imageID, String imageName, String imageUrl,
                 String type, List<Page> pageList, List<Room> roomList) {
        this.imageID = imageID;
        this.imageName = imageName;
        this.imageUrl = imageUrl;
        this.type = type;
        this.pageList = pageList;
        this.roomList = roomList;
    }

    public Image() {
    }
}
