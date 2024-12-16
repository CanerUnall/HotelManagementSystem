package com.cap.MolvenoLakeResort.controller.user;

import com.cap.MolvenoLakeResort.payload.response.user.UserRoleTypeResponseDto;
import com.cap.MolvenoLakeResort.service.user.UserRoleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/userRoleType")
public class UserRoleTypeController {
    private final UserRoleTypeService userRoleTypeService;

    @Autowired
    public UserRoleTypeController(UserRoleTypeService userRoleTypeService) {
        this.userRoleTypeService = userRoleTypeService;
    }


    @GetMapping
    public ResponseEntity<List<UserRoleTypeResponseDto>> getAllUserRoleType() {
        return ResponseEntity.ok(userRoleTypeService.getAllUserRoleType());
    }

    public ResponseEntity<UserRoleTypeResponseDto> save() {
        return null;//Burasi unutma
    }


}
