package com.cap.MolvenoLakeResort.controller.pages;

import com.cap.MolvenoLakeResort.payload.request.page.PageRequestDto;
import com.cap.MolvenoLakeResort.payload.response.page.PageResponseDto;
import com.cap.MolvenoLakeResort.service.media.ImageService;
import com.cap.MolvenoLakeResort.service.pages.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/pages")
public class PageController {

    private final PageService pageService;
    private final ImageService imageService;

    @Autowired
    public PageController(PageService pageService, ImageService imageService) {
        this.pageService = pageService;
        this.imageService = imageService;
    }


    @GetMapping
    public ResponseEntity<List<PageResponseDto>> getAllPages(){
        return ResponseEntity.ok(pageService.getAllPage());
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<PageResponseDto> createPage(
            @RequestParam("pageId") Long pageId,
            @RequestParam("pageName") String pageName,
            @RequestParam("content") String content,
            @RequestParam("photos") List<MultipartFile> photos) {

        PageRequestDto pageRequestDto = new PageRequestDto();
        pageRequestDto.setPageId(pageId);
        pageRequestDto.setPageName(pageName);
        pageRequestDto.setContent(content);
        pageRequestDto.setPhotos(photos);

        PageResponseDto createdPage = pageService.createPage(pageRequestDto);
        return new ResponseEntity<>(createdPage, HttpStatus.CREATED);
    }

    @DeleteMapping("/{pageId}")
    public ResponseEntity<String> deletePageById(@PathVariable("pageId") Long pageId){
        String result = pageService.deletePageById(pageId);
        return ResponseEntity.ok(result);

    }

}