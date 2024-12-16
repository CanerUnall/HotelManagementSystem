package com.cap.MolvenoLakeResort.payload.mappers;

import com.cap.MolvenoLakeResort.model.user.UserRoleType;
import com.cap.MolvenoLakeResort.payload.request.user.UserRoleTypeRequestDto;
import com.cap.MolvenoLakeResort.payload.response.user.UserRoleTypeResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRoleTypeMapper {

                             // UserRoleType listesini UserRoleTypeResponseDto listesine dönüştürür.
    public List<UserRoleTypeResponseDto> mapUserTypeRoleToUserResponseDtoList(List<UserRoleType> userRoleTypeList) {
        List<UserRoleTypeResponseDto> userRoleTypeResponseDtoList = new ArrayList<>();
        for (UserRoleType userRoleType : userRoleTypeList) {
            userRoleTypeResponseDtoList.add(toUserRoleTypeResponseDto(userRoleType));
        }
        return userRoleTypeResponseDtoList;
    }

                                           // UserRoleTypeRequestDto'yu UserRoleType nesnesine dönüştürür.
    public UserRoleType toUserRoleType(UserRoleTypeRequestDto userRoleTypeRequestDto) {
        if (userRoleTypeRequestDto == null) {
            return null;
        }
        UserRoleType userRoleType = new UserRoleType();
        userRoleType.setUserRoleTypeName(userRoleTypeRequestDto.getRoleName());
        return userRoleType;
    }

                         // UserRoleType nesnesini UserRoleTypeResponseDto'ya dönüştürür.
    public UserRoleTypeResponseDto toUserRoleTypeResponseDto(UserRoleType userRoleType) {
        if (userRoleType == null) {
            return null;
        }
        UserRoleTypeResponseDto userRoleTypeResponseDto = new UserRoleTypeResponseDto();
        userRoleTypeResponseDto.setId(userRoleType.getUserRoleTypeId());
        userRoleTypeResponseDto.setRoleName(userRoleType.getUserRoleTypeName());
        return userRoleTypeResponseDto;
    }
}
