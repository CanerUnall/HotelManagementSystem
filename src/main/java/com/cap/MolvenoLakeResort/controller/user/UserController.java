package com.cap.MolvenoLakeResort.controller.user;

import com.cap.MolvenoLakeResort.payload.request.user.*;
import com.cap.MolvenoLakeResort.config.SecurityUtil;
import com.cap.MolvenoLakeResort.payload.response.user.UserResponseDto;
import com.cap.MolvenoLakeResort.service.email.EmailService;
import com.cap.MolvenoLakeResort.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {


    private final UserService userService;
    private final EmailService emailService;

    private final SecurityUtil securityUtil;

    @Autowired
    public UserController(UserService userService, SecurityUtil securityUtil, EmailService emailService) {

        this.userService = userService;
        this.emailService = emailService;
        this.securityUtil = securityUtil;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> userResponseDtos = userService.getAllUsers();
        return ResponseEntity.ok(userResponseDtos);
    }

    @DeleteMapping("/{userId}")          /////////////////////
    public ResponseEntity<String> deleteUserByEmail(@PathVariable Long userId) {
        String result = userService.deleteUserByEmail(userId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/save")
    public ResponseEntity<String> registerUser(@RequestBody UserRequestDto userRequestDto) {
        userService.save(userRequestDto);
        return ResponseEntity.ok("User saved successfully");
    }

    @PutMapping("/edit/{email}")
    public ResponseEntity<String> updateUser(@PathVariable String email, @RequestBody UserRequestDto userRequestDto) {
        String result = userService.updateUser(email, userRequestDto);
        System.out.println("result = " + result);
        return ResponseEntity.ok("User updated successfully");
    }

    @PostMapping("/register")
    public String register(@RequestBody UserRequestDto userRequestDto) {

        return userService.save(userRequestDto);
    }

    @PostMapping("/check-email")
    public ResponseEntity<EmailCheckResponse> checkEmail(@RequestBody EmailCheckRequest emailCheckRequest) {
        boolean exists = userService.checkEmailExists(emailCheckRequest.getEmail());
        return ResponseEntity.ok(new EmailCheckResponse(exists));
    }

    // Kullanıcının kendi profil bilgilerini almak için endpoint
    @GetMapping("/profile")
    public ResponseEntity<UserResponseDto> getMyUser() {
        System.out.println("calisti");
        String email = securityUtil.getCurrentUserEmail();
        System.out.println(email);
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    // Kullanıcının kendi profil bilgilerini güncellemek için endpoint
    @PostMapping("/profile")
    public ResponseEntity<String> updateProfile(@RequestBody UserRequestDto userRequestDto) {

        String email = securityUtil.getCurrentUserEmail();
        System.out.println("userRequestDto.getUserId() = " + userRequestDto.getUserId());

        userService.updateProfile(email, userRequestDto);

        return ResponseEntity.ok("Profile updated successfully");
    }


    @PostMapping("/resetPassword")
    public ResponseEntity<String> processResetPassword(HttpServletRequest request, @RequestBody EmailCheckRequest resetPasswordRequest) {
        String email = resetPasswordRequest.getEmail();
        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(email, token);

        String resetUrl = request.getRequestURL().toString().replace(request.getServletPath(), "")
                + "/changePassword?token=" + token;

        String message = "Please click on the link below to reset your password.:\n" + resetUrl;

        // try {
        //     emailService.sendEmail(email, "Password Reset Request", message);
        // } catch (IOException e) {
        //     e.printStackTrace();
        //     return ResponseEntity.badRequest().body("Email sending error");
        // }

        return ResponseEntity.ok("Password reset link sent");
    }

    @PostMapping("/savePassword")
    public ResponseEntity<String> savePassword(@RequestBody SavePasswordRequest savePasswordRequest) {
        String token = savePasswordRequest.getToken();
        String password = savePasswordRequest.getPassword();
        userService.changeUserPassword(token, password);
        return ResponseEntity.ok("Password updated successfully");
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        System.out.println("changePasswordRequest.getNewPassword() = " + changePasswordRequest.getNewPassword());
        System.out.println("changePasswordRequest.getEmail() = " + changePasswordRequest.getEmail());
        System.out.println("changePasswordRequest.getUserId() = " + changePasswordRequest.getUserId());


        userService.changeUserPasswordByEmail(changePasswordRequest.getEmail(), changePasswordRequest.getNewPassword());
        return ResponseEntity.ok("User Password Updated");
    }


    //@PutMapping("/edit/{email}")
    //    public ResponseEntity<String> updateUser(@PathVariable String email, @RequestBody UserRequestDto userRequestDto) {
    //       String result = userService.updateUser(email, userRequestDto);
    //        System.out.println("result = " + result);
    //        return ResponseEntity.ok("User updated successfully");
    //    }

    @GetMapping("/checkUserInfo")
    public ResponseEntity<UserResponseDto> getUserByEmail(@RequestParam String email) {
        System.out.println("email = " + email);
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("getEmail")
    public String getEmail(){

        return securityUtil.getCurrentUserEmail();
    }


}
