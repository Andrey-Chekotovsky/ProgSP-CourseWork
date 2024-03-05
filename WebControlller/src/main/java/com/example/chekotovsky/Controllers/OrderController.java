package com.example.chekotovsky.Controllers;

import com.example.chekotovsky.Constants.HttpStatusCodes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.db.Models.Order;
import org.service.Dto.CompanyDto;
import org.service.Dto.OrderDetailsDto;
import org.service.Dto.OrderDto;
import org.service.Dto.OrderProductInfoDto;
import org.service.Service.OrderService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/orders")
public class OrderController {
    private ObjectMapper jsonMapper = new ObjectMapper();
    private final OrderService orderService;
    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders() {
        var order = orderService.getOrders();
        return new ResponseEntity<>(order, HttpStatusCode.valueOf(HttpStatusCodes.OK));
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDetailsDto> getOrder(@PathVariable(value="id") int id) {
        var order = orderService.getOrder(id);
        return new ResponseEntity<>(order, HttpStatusCode.valueOf(HttpStatusCodes.OK));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteOrder(@PathVariable(value="id") int id) {
        orderService.deleteOrder(id);
        return new ResponseEntity(HttpStatusCode.valueOf(HttpStatusCodes.OK));
    }
    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody String body) throws JsonProcessingException {
        System.out.println(body);
        var dto = jsonMapper.readValue(body, OrderDetailsDto.class);
        var createdOrder = orderService.createOrder(dto);
        return new ResponseEntity<>(createdOrder, HttpStatusCode.valueOf(HttpStatusCodes.OK));
    }
    @GetMapping(value = "/company/{id}")
    public ResponseEntity<List<OrderProductInfoDto>> getCompanySales(@PathVariable(value = "id") int id) {
        var sales = orderService.getCompanySales(id);
        return new ResponseEntity<>(sales, HttpStatusCode.valueOf(HttpStatusCodes.OK));
    }
}
