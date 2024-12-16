package com.cap.MolvenoLakeResort.service.user;

import com.cap.MolvenoLakeResort.model.user.UserRoleType;
import com.cap.MolvenoLakeResort.payload.request.user.UserRoleTypeRequestDto;
import com.cap.MolvenoLakeResort.payload.response.user.UserRoleTypeResponseDto;

import java.util.List;

public interface UserRoleTypeService {
    // Tüm kullanıcı rol türlerini getirmek için metod
    List<UserRoleTypeResponseDto> getAllUserRoleType();

    // Rol adına göre kullanıcı rol türünü getirmek için metod
    UserRoleType getUserRoleTypeByRoleName(String roleName);

    // Yeni kullanıcı rol türü oluşturmak için metod
    UserRoleTypeResponseDto save(UserRoleTypeRequestDto userRoleTypeRequestDto);

    // Rol ID'sine göre kullanıcı rol türünü getirmek için metod
    UserRoleTypeResponseDto getUserRoleTypeById(Long id);

    // Mevcut bir kullanıcı rol türünü güncellemek için metod
    UserRoleTypeResponseDto updateRole(Long id, UserRoleTypeRequestDto userRoleTypeRequestDto);

    // Bir kullanıcı rol türünü silmek için metod
    void deleteRole(Long id);
}
