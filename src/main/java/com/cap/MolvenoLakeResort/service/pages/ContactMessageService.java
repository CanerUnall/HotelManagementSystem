package com.cap.MolvenoLakeResort.service.pages;

import com.cap.MolvenoLakeResort.payload.request.page.ContactMessageRequestDto;
import com.cap.MolvenoLakeResort.payload.response.page.ContactMessageResponseDto;

import java.util.List;

public interface ContactMessageService {
    void save(ContactMessageRequestDto contactMessageRequestDto);

    List<ContactMessageResponseDto> getAllContactMessages();

    String deleteById(Long contactMessageId);
}
