package com.cap.MolvenoLakeResort.controller;

import com.cap.MolvenoLakeResort.model.user.User;
import com.cap.MolvenoLakeResort.repository.user.UserRepository;
import com.cap.MolvenoLakeResort.service.user.UserRoleTypeService;
import com.cap.MolvenoLakeResort.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;

@Controller
@RequestMapping()
public class AuthController {

    private final UserRoleTypeService userRoleTypeService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final UserRepository userRepository;
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleResolver localeResolver;
    @Autowired
    public AuthController(UserRoleTypeService userRoleTypeService, PasswordEncoder passwordEncoder, UserService userService, UserRepository userRepository) {
        this.userRoleTypeService = userRoleTypeService;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    // http://localhost:9090/login-fail
    @GetMapping("/login-fail")
    public String loginFailPage(Model model) {
        model.addAttribute("errorMessage", "Login failed. Please check your credentials and try again.");
        return "Home";
    }


//    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
//    public ResponseEntity<UserResponseDto> login(@RequestBody UserRequestDto loginRequestDto) {
//        UserResponseDto userResponseDto = userService.login(loginRequestDto);
//        return ResponseEntity.ok(userResponseDto);
//    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") User user, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Locale locale = localeResolver.resolveLocale(request);
        try {
            user.setUserRoleType(userRoleTypeService.getUserRoleTypeByRoleName("GUEST"));
            userService.register(user);
            String successMessage = messageSource.getMessage("registration.success", null, locale);
            redirectAttributes.addFlashAttribute("successMessage", successMessage);
        } catch (Exception e) {
            String errorMessage = messageSource.getMessage("registration.failure", null, locale);
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
        }
        return "redirect:/Home";
    }


    @GetMapping("/login")
    public String getLoginPage(@RequestParam(value = "error", required = false) String error, Model model, HttpSession session) {
        if (error != null) {
            model.addAttribute("errorMessage", "Login failed. Please check your credentials and try again.");
        }
        String successMessage = (String) session.getAttribute("successMessage");
        if (successMessage != null) {
            model.addAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }
        return "Home";
    }

//    @GetMapping("/logout")
//    public String logout() {
//        return "Home";
//    }
}