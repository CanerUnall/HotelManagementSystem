package com.cap.MolvenoLakeResort.service.pages;

import com.cap.MolvenoLakeResort.model.media.Image;
import com.cap.MolvenoLakeResort.model.pages.Page;
import com.cap.MolvenoLakeResort.payload.mappers.PageMapper;
import com.cap.MolvenoLakeResort.payload.request.media.ImageRequestDto;
import com.cap.MolvenoLakeResort.payload.request.page.PageRequestDto;
import com.cap.MolvenoLakeResort.payload.response.page.PageResponseDto;
import com.cap.MolvenoLakeResort.repository.pages.PageRepository;
import com.cap.MolvenoLakeResort.service.media.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PageServiceImpl implements PageService {


    private final PageRepository pageRepository;
    private final ImageService imageService;
    private final PageMapper pageMapper;

    @Autowired
    public PageServiceImpl(PageRepository pageRepository, ImageService imageService, PageMapper pageMapper) {
        this.pageRepository = pageRepository;
        this.imageService = imageService;
        this.pageMapper = pageMapper;
    }


    @Override
    public PageResponseDto createPage(PageRequestDto pageRequestDto) {


        // 2. Sayfayı oluştur
        Page page = new Page();
        page.setPageId(pageRequestDto.getPageId());
        page.setPageName(pageRequestDto.getPageName());
        page.setContent(pageRequestDto.getContent());


        //fotograflari setliyorum. burasi kafa karistirici
        List<Image> images = pageRequestDto.getPhotos().stream()
                .map(photo -> {
                    ImageRequestDto imageRequestDto = new ImageRequestDto();
                    imageRequestDto.setName(photo.getOriginalFilename());
                    try {
                        imageRequestDto.setUrl(Base64.getEncoder().encodeToString(photo.getBytes()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return imageService.saveImage(imageRequestDto, "PAGE");
                })
                .collect(Collectors.toList());
        page.setImageList(images);

        Page savedPage = pageRepository.save(page);

        // 4. Response DTO oluştur
        PageResponseDto pageResponseDto = new PageResponseDto();
        pageResponseDto.setPageId(savedPage.getPageId());
        pageResponseDto.setPageName(savedPage.getPageName());
        pageResponseDto.setContent(savedPage.getContent());
        pageResponseDto.setImageUrlList(savedPage.getImageList().stream().map(Image::getImageUrl).collect(Collectors.toList()));

        return pageResponseDto;
    }

    @Override
    public List<PageResponseDto> getAllPage() {
        List<PageResponseDto> pageResponseDtoList = new ArrayList<>();
        for (Page page : pageRepository.findAll()) {
            pageResponseDtoList.add(pageMapper.mapPagetoPageResponseDto(page));
        }
        return pageResponseDtoList;

    }

    @Override
    public String deletePageById(Long pageId) {
        pageRepository.deleteById(pageId);
        return "success";
    }
}

