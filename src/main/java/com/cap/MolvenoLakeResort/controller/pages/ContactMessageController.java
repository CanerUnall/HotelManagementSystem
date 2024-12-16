package com.cap.MolvenoLakeResort.controller.pages;

import com.cap.MolvenoLakeResort.payload.request.page.ContactMessageRequestDto;
import com.cap.MolvenoLakeResort.payload.response.page.ContactMessageResponseDto;
import com.cap.MolvenoLakeResort.service.pages.ContactMessageService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contactMessages")
public class ContactMessageController {

    private final ContactMessageService contactMessageService;

    @Autowired
    public ContactMessageController(ContactMessageService contactMessageService) {
        this.contactMessageService = contactMessageService;
    }


    @PostMapping
    public ResponseEntity<String> save(@RequestBody ContactMessageRequestDto contactMessageRequestDto){

        contactMessageService.save(contactMessageRequestDto);

        return ResponseEntity.ok("success");
    }

    @GetMapping
    public ResponseEntity<List<ContactMessageResponseDto>> getAll(){

        return ResponseEntity.ok(contactMessageService.getAllContactMessages());
    }

    @DeleteMapping("/{contactMessageId}")
    public ResponseEntity<String> deleteById(@PathVariable Long contactMessageId){

        return ResponseEntity.ok(contactMessageService.deleteById(contactMessageId));

    }


}
