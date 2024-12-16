package com.cap.MolvenoLakeResort.service.pages;

import com.cap.MolvenoLakeResort.payload.request.page.PageRequestDto;
import com.cap.MolvenoLakeResort.payload.response.page.PageResponseDto;

import java.util.List;

public interface PageService {
    PageResponseDto createPage(PageRequestDto pageRequestDto);

    List<PageResponseDto> getAllPage();

    String deletePageById(Long pageId);
}
