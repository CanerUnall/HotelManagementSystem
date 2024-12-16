package com.cap.MolvenoLakeResort.service.pages;

import com.cap.MolvenoLakeResort.model.pages.ContactMessage;
import com.cap.MolvenoLakeResort.payload.mappers.ContactMessageMapper;
import com.cap.MolvenoLakeResort.payload.request.page.ContactMessageRequestDto;
import com.cap.MolvenoLakeResort.payload.response.page.ContactMessageResponseDto;
import com.cap.MolvenoLakeResort.repository.pages.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactMessageServiceImpl implements ContactMessageService {
    private final ContactMessageMapper contactMessageMapper;
    private final ContactMessageRepository contactMessageRepository;

    @Autowired
    public ContactMessageServiceImpl(ContactMessageMapper contactMessageMapper, ContactMessageRepository contactMessageRepository) {
        this.contactMessageMapper = contactMessageMapper;
        this.contactMessageRepository = contactMessageRepository;
    }

    @Override
    public void save(ContactMessageRequestDto contactMessageRequestDto) {

        ContactMessage contactMessage = contactMessageMapper.mapContactMessageRequestDtoToContactMessage(contactMessageRequestDto);

        contactMessage = contactMessageRepository.save(contactMessage);


    }

    @Override
    public List<ContactMessageResponseDto> getAllContactMessages() {

        List<ContactMessage> contactMessageList = contactMessageRepository.findAll();

        List<ContactMessageResponseDto> contactMessageResponseDtoList = contactMessageMapper.mapContactMessageListToContactMessageResponseList(contactMessageList);

        return contactMessageResponseDtoList;
    }

    @Override
    public String deleteById(Long contactMessageId) {

        contactMessageRepository.deleteById(contactMessageId);

        return "success";

    }
}
