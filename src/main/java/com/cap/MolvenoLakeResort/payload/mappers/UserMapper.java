package com.cap.MolvenoLakeResort.payload.mappers;

import com.cap.MolvenoLakeResort.model.user.User;
import com.cap.MolvenoLakeResort.payload.request.user.UserRequestDto;
import com.cap.MolvenoLakeResort.payload.response.user.UserResponseDto;
import com.cap.MolvenoLakeResort.service.user.UserRoleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    private final PasswordEncoder passwordEncoder;
    private final UserRoleTypeService userRoleTypeService;

    @Autowired
    public UserMapper(PasswordEncoder passwordEncoder, UserRoleTypeService userRoleTypeService) {
        this.passwordEncoder = passwordEncoder;
        this.userRoleTypeService = userRoleTypeService;
    }

    public List<UserResponseDto> mapUserListToUserResponseDtoList(List<User> userList) {
        List<UserResponseDto> userResponseDtos = new ArrayList<>();

        for (User user : userList) {
            UserResponseDto userResponseDto = mapUserToUserResponseDto(user);
            userResponseDtos.add(userResponseDto);
        }

        return userResponseDtos;
    }

    public User mapUserRequestDtoToUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setUserId(userRequestDto.getUserId());
        user.setUserName(userRequestDto.getUserName());
        user.setUserSurName(userRequestDto.getUserSurName());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        user.setAddress(userRequestDto.getAddress());


        // userRoleType null olabilir kontrolü
        if (userRequestDto.getUserRoleType() != null) {
            user.setUserRoleType(userRoleTypeService.getUserRoleTypeByRoleName(userRequestDto.getUserRoleType()));
        } else {
            user.setUserRoleType(null);
        }

        user.setPhoneNumber(userRequestDto.getPhoneNumber());
        user.setEmail(userRequestDto.getEmail());
        return user;
    }

    public UserResponseDto mapUserToUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUserId(user.getUserId());
        userResponseDto.setUserName(user.getUserName());
        userResponseDto.setPassword(user.getPassword());
        userResponseDto.setUserSurName(user.getUserSurName());
        userResponseDto.setAddress(user.getAddress());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPhoneNumber(user.getPhoneNumber());
        userResponseDto.setAdult(user.isAdult());

        // userRoleType null ise, null döndür ya da varsayılan bir değer ata
        if (user.getUserRoleType() != null) {
            userResponseDto.setUserRoleType(user.getUserRoleType().getUserRoleTypeName());
            System.out.println("user.getUserRoleType().getUserRoleTypeName() = " + user.getUserRoleType().getUserRoleTypeName());
        } else {
            userResponseDto.setUserRoleType(null); // ya da varsayılan bir değer
        }

        return userResponseDto;
    }
}
