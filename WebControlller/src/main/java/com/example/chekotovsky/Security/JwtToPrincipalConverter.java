package com.example.chekotovsky.Security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.service.Security.UserPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtToPrincipalConverter {
    public UserPrincipal convert(DecodedJWT jwt) {
        UserPrincipal principal =  UserPrincipal.builder()
                .userId(Long.parseLong(jwt.getSubject()))
                .username(jwt.getClaim("username").asString())
                .authorities(extractAuthoritiesFromClaim(jwt))
                .build();
        return principal;
    }

    private List<SimpleGrantedAuthority> extractAuthoritiesFromClaim(DecodedJWT jwt) {
        var claim = jwt.getClaim("permissions");
        if (claim.isNull() || claim.isMissing()) return List.of();
        return claim.asList(SimpleGrantedAuthority.class);
    }
}
