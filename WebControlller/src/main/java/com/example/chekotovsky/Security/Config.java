package com.example.chekotovsky.Security;

import org.db.Models.Message;
import org.db.Models.Role;
import org.db.Models.User;
import org.service.Dto.LoginResponseDto;
import org.service.Dto.MessageDto;
import org.service.Dto.RegisterRequestDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.print.Book;

@Configuration
public class Config {
    @Bean
    public Book book() {
        return new Book();
    }
}
