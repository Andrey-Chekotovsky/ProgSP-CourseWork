package com.example.chekotovsky.Controllers;

import com.example.chekotovsky.Constants.HttpStatusCodes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.service.Dto.*;
import org.service.Mappers.Mapper;
import org.service.Mappers.UserUserDetailsDtoMapper;
import lombok.RequiredArgsConstructor;
import org.db.Models.User;
import org.service.Service.UserService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {
    private ObjectMapper jsonMapper = new ObjectMapper();
    private final UserService userService;
    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        var users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatusCode.valueOf(HttpStatusCodes.OK));
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDetailsDto> getUser(@PathVariable(value="id") int id) {
        var user = userService.getUser(id);
        return new ResponseEntity<>(user, HttpStatusCode.valueOf(HttpStatusCodes.OK));
    }
    @PostMapping(value = "/subscribe")
    public ResponseEntity subscribe(@RequestBody String body) throws JsonProcessingException {
        var dto = jsonMapper.readValue(body, CompanyUserRelationsDto.class);
        userService.subscribe(dto);
        return new ResponseEntity<>( HttpStatusCode.valueOf(HttpStatusCodes.OK));
    }
    @PostMapping(value = "/unsubscribe")
    public ResponseEntity unsubscribe(@RequestBody String body) throws JsonProcessingException {
        var dto = jsonMapper.readValue(body, CompanyUserRelationsDto.class);
        userService.unsubscribe(dto);
        return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatusCodes.OK));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteUser(@PathVariable(value="id") int id) {
        userService.deleteUser(id);
        return new ResponseEntity(HttpStatusCode.valueOf(HttpStatusCodes.OK));
    }
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody User user) {
        var createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatusCode.valueOf(HttpStatusCodes.CREATED));
    }
    @PatchMapping
    public ResponseEntity updateUser(@RequestBody String body) throws JsonProcessingException {
        var dto = jsonMapper.readValue(body, UserDto.class);
        userService.updateUser(dto);
        return new ResponseEntity(HttpStatusCode.valueOf(HttpStatusCodes.OK));
    }
    @PatchMapping("/ban/{id}")
    public ResponseEntity ban(@PathVariable(value="id") int id) throws JsonProcessingException {
        userService.ban(id);
        return new ResponseEntity(HttpStatusCode.valueOf(HttpStatusCodes.OK));
    }
    @PatchMapping("/authority")
    public ResponseEntity grantAuthority(@RequestBody String body) throws JsonProcessingException {
        var dto = jsonMapper.readValue(body, AuthorityDto.class);
        userService.grantAuthority(dto);
        return new ResponseEntity(HttpStatusCode.valueOf(HttpStatusCodes.OK));
    }
}
