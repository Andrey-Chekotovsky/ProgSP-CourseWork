package org.service.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.db.Models.User;
import org.springframework.security.core.GrantedAuthority;


import java.util.Collection;

@AllArgsConstructor

public class CustomUserDetails implements org.springframework.security.core.userdetails.UserDetails{
    @Getter
    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
