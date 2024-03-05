package com.example.chekotovsky.Controllers;

import com.example.chekotovsky.Constants.HttpStatusCodes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.db.Models.Company;
import org.db.Models.Message;
import org.service.Dto.MessageDto;
import org.service.Service.CompanyService;
import org.service.Service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/messages")
public class MessageController {
    private final MessageService messageService;
    private ObjectMapper jsonMapper = new ObjectMapper();

    @RequestMapping(value = "/user/{id}", method = RequestMethod.OPTIONS)
    public ResponseEntity options(HttpServletResponse response) {
        response.setHeader("Allow", "GET");
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping(value = "/user/{id}")
    public ResponseEntity<List<MessageDto>> getUsersMessages(@PathVariable(value="id") int id) {
        var messages = messageService.getUsersMessages(id);
        return new ResponseEntity<>(messages, HttpStatusCode.valueOf(HttpStatusCodes.OK));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteMessage(@PathVariable(value="id") int id) {
        messageService.deleteMessage(id);
        return new ResponseEntity(HttpStatusCode.valueOf(HttpStatusCodes.OK));
    }
    @PostMapping
    public ResponseEntity<MessageDto> createMessage(@RequestBody String body) throws JsonProcessingException {
        var message = jsonMapper.readValue(body, MessageDto.class);
        var createdMessage = messageService.createMessage(message);
        return new ResponseEntity<>(createdMessage, HttpStatusCode.valueOf(HttpStatusCodes.CREATED));
    }
    @PostMapping("/all")
    public ResponseEntity<MessageDto> sendToAllMessage(@RequestBody String body) throws JsonProcessingException {
        var message = jsonMapper.readValue(body, MessageDto.class);
        var createdMessage = messageService.toAllMessage(message);
        return new ResponseEntity<>(createdMessage, HttpStatusCode.valueOf(HttpStatusCodes.CREATED));
    }
}
