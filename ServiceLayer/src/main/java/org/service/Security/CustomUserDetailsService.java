package org.service.Security;



import lombok.RequiredArgsConstructor;
import org.service.Service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userService.getUserByUsername(username);
        return UserPrincipal.builder()
                .userId(Long.valueOf(user.get().getId()))
                .username(user.get().getUsername())
                .password(user.get().getPassword())
                .authorities(List.of(new SimpleGrantedAuthority(user.get().getStringRole())))
                .build();
    }
}
