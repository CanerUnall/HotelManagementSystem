package com.cap.MolvenoLakeResort.payload.request.page;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class PageRequestDto {
    private Long pageId;
    private String pageName;
    private String content;
    private List<MultipartFile> photos;

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

    public List<MultipartFile> getPhotos() {
        return photos;
    }

    public void setPhotos(List<MultipartFile> photos) {
        this.photos = photos;
    }
}
