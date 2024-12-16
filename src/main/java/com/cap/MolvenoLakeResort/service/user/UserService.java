package com.cap.MolvenoLakeResort.service.user;

import com.cap.MolvenoLakeResort.model.user.PasswordResetToken;
import com.cap.MolvenoLakeResort.model.user.User;
import com.cap.MolvenoLakeResort.payload.request.user.UserRequestDto;
import com.cap.MolvenoLakeResort.payload.response.user.UserResponseDto;

import java.util.List;

public interface UserService {
    boolean checkEmailExists(String email);

    List<UserResponseDto> getAllUsers();

    String deleteUserByEmail(Long userId);

    String save(UserRequestDto userRequestDto);

    String updateUser(String email, UserRequestDto userDetails);


//    UserResponseDto login(UserRequestDto loginRequestDto);

    //    public UserResponseDto login(UserRequestDto loginRequestDto) {
    //        Optional<User> userOptional = userRepository.findByEmail(loginRequestDto.getEmail());
    //        if (userOptional.isPresent()) {
    //            User user = userOptional.get();
    //            if (passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
    //                // Kullanıcı doğrulandı
    //                UserResponseDto responseDto = new UserResponseDto();
    //                responseDto.setUserName(user.getUserName());
    //                responseDto.setEmail(user.getEmail());
    //                return responseDto;
    //            }
    //        }
    //
    //        throw new RuntimeException("Kullanıcı bilgisi veya sifre hatali. Lütfen tekrar deneyin.");
    //
    //    }
    User findByLogin(String email);

    void register(User user) ;

    PasswordResetToken getPasswordResetToken(String token);

    void changeUserPassword(String token, String password);

    void createPasswordResetTokenForUser(String email, String token);

    UserResponseDto getUserByEmail(String email);

    void updateProfile(String email, UserRequestDto userRequestDto);

    void changeUserPasswordByEmail(String email, String newPassword);
}
