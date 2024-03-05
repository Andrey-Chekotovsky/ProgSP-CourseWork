package com.example.chekotovsky.Controllers;

import com.example.chekotovsky.Constants.HttpStatusCodes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.db.Models.User;
import org.service.Dto.LoginRequestDto;
import org.service.Dto.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.db.Models.Role;
import org.service.Dto.RegisterRequestDto;
import org.service.Security.JwtIssuer;
import org.service.Security.UserPrincipal;
import org.service.Service.UserService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AuthController {
    private final JwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private ObjectMapper jsonMapper = new ObjectMapper();
    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody String loginDto) throws JsonProcessingException {

        var dto = jsonMapper.readValue(loginDto, LoginRequestDto.class);
        System.out.println(dto.getUsername() + dto.getPassword());
        var authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var principal = (UserPrincipal)authentication.getPrincipal();
        var roles =  principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        var token = jwtIssuer.issue(Long.valueOf(principal.getUserId()).intValue(),
                principal.getUsername(), roles);
        var user = userService.getUser(Long.valueOf(principal.getUserId()).intValue());
        var response = new LoginResponseDto(
                Long.valueOf(principal.getUserId()).intValue(),
                token, user.getRole(), user.getCompany(), user.isBanned());
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(HttpStatusCodes.OK));
    }
    @PostMapping(value = "/register")
    public ResponseEntity<LoginResponseDto> register(@RequestBody String json) throws JsonProcessingException {
        var dto = jsonMapper.readValue(json, RegisterRequestDto.class);
        User user = userService.register(dto);
        var token = jwtIssuer.issue(user.getId(),
                user.getUsername(), List.of(user.getStringRole()));
        var response = new LoginResponseDto(
                user.getId(), token, user.getRole(), null, user.isBanned());
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(HttpStatusCodes.CREATED));
    }
}
