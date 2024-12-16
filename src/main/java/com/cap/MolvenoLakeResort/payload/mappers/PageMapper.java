package com.cap.MolvenoLakeResort.payload.mappers;

import com.cap.MolvenoLakeResort.model.media.Image;
import com.cap.MolvenoLakeResort.model.pages.Page;
import com.cap.MolvenoLakeResort.payload.response.page.PageResponseDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PageMapper {
    public PageResponseDto mapPagetoPageResponseDto(Page page) {

        PageResponseDto pageResponseDto = new PageResponseDto();
        pageResponseDto.setContent(page.getContent());
        pageResponseDto.setPageName(page.getPageName());
        pageResponseDto.setPageId(page.getPageId());
        pageResponseDto.setImageUrlList(page.getImageList().stream().map(Image::getImageUrl).collect(Collectors.toList()));

        return pageResponseDto;
    }
}
