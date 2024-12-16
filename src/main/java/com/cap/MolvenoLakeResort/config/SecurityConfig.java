package com.cap.MolvenoLakeResort.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Diğer bean tanımlamalarınız

    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    // CustomAuthenticationSuccessHandler'ı Bean olarak tanımlıyoruz
    @Bean
    public CustomAuthenticationSuccessHandler successHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers(header -> header.frameOptions(options -> options.sameOrigin()));
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/",
                                "/h2-console/**",
                                "/console/**",
                                "/js/**",
                                "/registration",
                                "/css/**",
                                "/img/**",
                                "/api/**",
                                "/i18n/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/change-language**",
                                "/room",
                                "/user",
                                "/bookNow",
                                "/bookNow/**",
                                "/pages",
                                "/dirtyRooms",
                                "/profile",
                                "/myReservations",
                                "/reservation",
                                "/bedTypes",
                                "/roomTypes",
                                "/contactMessages",
                                "/comments",
                                "/facilities",
                                "/carousels",
                                "/aboutUs",
                                "/amenities",
                                "/Home",
                                "/error",
                                "/resetPasswordRequest",
                                "/changePassword",
                                "/dirtyRooms",
                                "/hotelInformation",
                                "/managementSystem",
                                "/webjars/**"
                        ).permitAll()
                        .requestMatchers("/*", "/room*").hasAnyAuthority("GENERAL MANAGER")
                        .requestMatchers("/reservation*").hasAnyAuthority("GENERAL MANAGER")
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
                        .usernameParameter("email")
                        .successHandler(successHandler()) // Bean olarak tanımladığımız success handler'ı burada kullanıyoruz
                        .failureUrl("/login-fail")
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/Home")
                        .invalidateHttpSession(true)
                        .permitAll())
                .sessionManagement(session -> session
                        .sessionFixation().newSession()
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true));

        return http.build();
    }
}
