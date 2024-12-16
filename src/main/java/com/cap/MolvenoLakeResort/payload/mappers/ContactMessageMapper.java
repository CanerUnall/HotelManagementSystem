package com.cap.MolvenoLakeResort.payload.mappers;

import com.cap.MolvenoLakeResort.model.pages.ContactMessage;
import com.cap.MolvenoLakeResort.payload.request.page.ContactMessageRequestDto;
import com.cap.MolvenoLakeResort.payload.response.page.ContactMessageResponseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ContactMessageMapper {
    public ContactMessage mapContactMessageRequestDtoToContactMessage(ContactMessageRequestDto contactMessageRequestDto) {
        ContactMessage contactMessage = new ContactMessage();

        contactMessage.setContactMessageId(contactMessageRequestDto.getContactMessageId());
        contactMessage.setMessage(contactMessageRequestDto.getMessage());
        contactMessage.setAuthor(contactMessageRequestDto.getAuthor());
        contactMessage.setEmail(contactMessageRequestDto.getEmail());
        contactMessage.setDateTime(LocalDateTime.now());

        return contactMessage;

    }

    public List<ContactMessageResponseDto> mapContactMessageListToContactMessageResponseList(List<ContactMessage> contactMessageList) {
        List<ContactMessageResponseDto> contactMessageResponseDtoList = new ArrayList<>();
        for (ContactMessage contactMessage:contactMessageList){

            contactMessageResponseDtoList.add(mapContactMessageToContactMessageResponseDto(contactMessage));

        }

        return contactMessageResponseDtoList;


    }

    public ContactMessageResponseDto mapContactMessageToContactMessageResponseDto(ContactMessage contactMessage){

        ContactMessageResponseDto contactMessageResponseDto = new ContactMessageResponseDto();
        contactMessageResponseDto.setAuthor(contactMessage.getAuthor());
        contactMessageResponseDto.setContactMessageId(contactMessage.getContactMessageId());
        contactMessageResponseDto.setCreationDateTime(contactMessage.getDateTime());
        contactMessageResponseDto.setEmail(contactMessage.getEmail());
        System.out.println(contactMessage.getEmail());
        contactMessageResponseDto.setMessage(contactMessage.getMessage());

        return contactMessageResponseDto;

    }

}
