package com.cap.MolvenoLakeResort.service.user;

import com.cap.MolvenoLakeResort.model.user.UserRoleType;
import com.cap.MolvenoLakeResort.payload.mappers.UserRoleTypeMapper;
import com.cap.MolvenoLakeResort.payload.request.user.UserRoleTypeRequestDto;
import com.cap.MolvenoLakeResort.payload.response.user.UserRoleTypeResponseDto;
import com.cap.MolvenoLakeResort.repository.user.UserRoleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleTypeServiceImpl implements UserRoleTypeService{
    private final UserRoleTypeRepository userRoleTypeRepository;
    private final UserRoleTypeMapper userRoleTypeMapper;

    @Autowired
    public UserRoleTypeServiceImpl(UserRoleTypeRepository userRoleTypeRepository, UserRoleTypeMapper userRoleTypeMapper) {
        this.userRoleTypeRepository = userRoleTypeRepository;
        this.userRoleTypeMapper = userRoleTypeMapper;
    }

    // Tüm kullanıcı rol türlerini getirmek için metod
    @Override
    public List<UserRoleTypeResponseDto> getAllUserRoleType() {
        List<UserRoleType> userRoleTypeList = userRoleTypeRepository.findAll();
        return userRoleTypeMapper.mapUserTypeRoleToUserResponseDtoList(userRoleTypeList);
    }

    // Rol adına göre kullanıcı rol türünü getirmek için metod
    @Override
    public UserRoleType getUserRoleTypeByRoleName(String roleName) {
        UserRoleType userRoleType = userRoleTypeRepository.findByUserRoleTypeName(roleName);
        return userRoleType;
    }

    // Yeni kullanıcı rol türü oluşturmak için metod
    @Override
    public UserRoleTypeResponseDto save(UserRoleTypeRequestDto userRoleTypeRequestDto) {
        UserRoleType userRoleType = userRoleTypeMapper.toUserRoleType(userRoleTypeRequestDto);
        UserRoleType savedUserRoleType = userRoleTypeRepository.save(userRoleType);
        return userRoleTypeMapper.toUserRoleTypeResponseDto(savedUserRoleType);
    }

    // Rol ID'sine göre kullanıcı rol türünü getirmek için metod
    @Override
    public UserRoleTypeResponseDto getUserRoleTypeById(Long id) {
        UserRoleType userRoleType = userRoleTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        return userRoleTypeMapper.toUserRoleTypeResponseDto(userRoleType);
    }

    // Mevcut bir kullanıcı rol türünü güncellemek için metod
    @Override
    public UserRoleTypeResponseDto updateRole(Long id, UserRoleTypeRequestDto userRoleTypeRequestDto) {
        UserRoleType userRoleType = userRoleTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        userRoleType.setUserRoleTypeName(userRoleTypeRequestDto.getRoleName());
        UserRoleType updatedUserRoleType = userRoleTypeRepository.save(userRoleType);
        return userRoleTypeMapper.toUserRoleTypeResponseDto(updatedUserRoleType);
    }

    // Bir kullanıcı rol türünü silmek için metod
    @Override
    public void deleteRole(Long id) {
        UserRoleType userRoleType = userRoleTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        userRoleTypeRepository.delete(userRoleType);
    }
}
