package com.cap.MolvenoLakeResort.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // Login başarılı olduğunda session'a bir mesaj ekliyoruz
        request.getSession().setAttribute("successMessage", "Login successful! Welcome back./登陆成功！欢迎回来。");
        System.out.println("Success message added to session: " + request.getSession().getAttribute("successMessage"));
        response.sendRedirect("/Home"); // Başarılı login'den sonra yönlendirilecek sayfa
    }
}