package com.example.chekotovsky.Controllers;

import com.example.chekotovsky.Constants.HttpStatusCodes;
import com.example.chekotovsky.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.db.Models.User;
import org.service.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@EnableMethodSecurity(securedEnabled = true)
@RestController
@RequestMapping("/books")
public class BookController {
    private List<BookDto> books = List.of(new BookDto().toBuilder()
            .name("name")
            .authort("author")
            .yearOfIssue(1788)
            .genre("genre")
            .build());
    @Secured("ADMIN'")
    @GetMapping("/get")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<List<BookDto>> getUser() {
        return new ResponseEntity<>(books, HttpStatusCode.valueOf(HttpStatusCodes.OK));
    }
}
