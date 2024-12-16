package com.cap.MolvenoLakeResort.config;

import com.cap.MolvenoLakeResort.model.user.User;
import com.cap.MolvenoLakeResort.model.user.UserRoleType;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        UserRoleType role = user.getUserRoleType();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();


            authorities.add(new SimpleGrantedAuthority(role.getUserRoleTypeName()));


        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName().toLowerCase();
    }

    public String getFullname() {
        return user.getUserName() + " " + user.getUserSurName();
    }

    public String getEmail(){
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}