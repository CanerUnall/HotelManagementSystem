package com.cap.MolvenoLakeResort.mvc;

import jakarta.servlet.http.Cookie;
import com.cap.MolvenoLakeResort.model.business.reservation.Reservation;
import com.cap.MolvenoLakeResort.model.user.PasswordResetToken;
import com.cap.MolvenoLakeResort.repository.business.reservation.ReservationRepository;
import com.cap.MolvenoLakeResort.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@Controller
public class MvcController {


    private final ReservationRepository reservationRepository;
    private final UserService userService;

    @Autowired
    public MvcController(ReservationRepository reservationRepository, UserService userService) {
        this.reservationRepository = reservationRepository;
        this.userService = userService;
    }

    // http://localhost:9090/
    // http://localhost:9090/home
    @GetMapping({"/", "/Home"})
    public String homePage(HttpSession session, Model model) {
        String successMessage = (String) session.getAttribute("successMessage");
        String errorMessage = (String) session.getAttribute("errorMessage");

        if (successMessage != null) {
            model.addAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }

        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
            session.removeAttribute("errorMessage");
        }

        return "Home";
    }



    @GetMapping("/room")
    public String getRoom() {
        return "room";
    }

    @GetMapping("/user")
    public String getProfile() {
        return "user";
    }

    @GetMapping("/profile")
    public String getUser() {
        return "profile";
    }

    @GetMapping("/reservation")
    public String getReservation(HttpServletRequest httpRequest) {
        String value = httpRequest.getQueryString();
        System.out.println("value = " + value);
        return "reservation";
    }

    @GetMapping("/myReservations")
    public String getMyReservation() {

    return "myReservations";
    }

    @GetMapping("/change-language")
    public String changeLanguage(@RequestParam String lang, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute("lang", lang);
        Locale locale = new Locale(lang);
        request.setAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE", locale);

        // Çerez ekleme
        Cookie cookie = new Cookie("lang", lang);
        cookie.setMaxAge(60 * 60 * 24 * 30); // 30 gün geçerli olacak
        cookie.setPath("/");
        response.addCookie(cookie);

        // Dil değişimini konsola yazdır
        System.out.println("Language changed to: " + lang);

        // Referer header'ını al ve geldiği sayfaya yönlendir
        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/");
    }

    @GetMapping("/pages")
    public String getPages() {
        return "pages";
    }

    @GetMapping("/bedTypes")
    public String getBedTypes() {
        return "bedType";
    }

    @GetMapping("/roomTypes")
    public String getRoomTypes() {
        return "roomType";
    }

    @GetMapping("/contactMessages")
    public String getContactMessages(){
        return "contactMessage";
    }

    @GetMapping("/comments")
    public String getComments(){
        return "comments";
    }

    @GetMapping("/facilities")
    public String getFacilities(){
        return "facilities";
    }

    @GetMapping("/carousels")
    public String getCarousels(){
        return "carousel";
    }

    @GetMapping("/aboutUs")
    public String getAboutUs(){
        return "aboutUs";
    }

    @GetMapping("/amenities")
    public String getAmenities(){
        return "hotelAmenities";
    }

    @GetMapping("/dirtyRooms")
    public String getDirtyRoomsFragment() {
        return "dirtyRooms";
    }

    @GetMapping("/resetPasswordRequest")
    public String resetPassword() {
        return "resetPasswordRequest";
    }

    @GetMapping("/changePassword")
    public String changePassword(@RequestParam("token") String token, Model model) {
        PasswordResetToken resetToken = userService.getPasswordResetToken(token);
        if (resetToken != null) {
            model.addAttribute("token", token);
            return "changePassword";
        } else {
            return "error"; // Hata sayfasına yönlendirin veya hata mesajı döndürün
        }
    }

//    @GetMapping("/bookNow")
//    public String showReservationPage() {
//        return "bookNow";
//    }

    @GetMapping("/bookNow")
    public String editReservationPage1(@RequestParam(required = false) Long reservationId, Model model) {
        if (reservationId != null) {
            // Rezervasyon bilgilerini veritabanından çek
            if (reservationRepository.findById(reservationId).isPresent()) {
                Reservation reservation = reservationRepository.findById(reservationId).get();
                model.addAttribute("reservation", reservation);
            }

        }
        return "bookNow"; // bookNow1.html dosyasına yönlendirme
    }


    @GetMapping("/hotelInformation")
    public String getHotelInformation(){
        return "hotelInformation";
    }
}