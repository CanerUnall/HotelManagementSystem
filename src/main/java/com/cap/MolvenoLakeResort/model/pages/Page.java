package com.cap.MolvenoLakeResort.model.pages;

import com.cap.MolvenoLakeResort.model.media.Image;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pageId;

    private String pageName;

    private String content;

    @ManyToMany
    @JoinTable(name = "page_images",
            joinColumns = @JoinColumn(name = "page_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private List<Image> imageList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page page = (Page) o;
        return Objects.equals(pageId, page.pageId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(pageId);
    }

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public Page(Long pageId, String pageName, String content, List<Image> imageList) {
        this.pageId = pageId;
        this.pageName = pageName;
        this.content = content;
        this.imageList = imageList;
    }

    public Page() {
    }

}
