package com.example.chekotovsky.Controllers;

import com.example.chekotovsky.Constants.HttpStatusCodes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.db.Models.Order;
import org.db.Models.Product;
import org.service.Dto.CompanyUserRelationsDto;
import org.service.Dto.ProductDto;
import org.service.Service.ProductService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/products")
public class ProductController {
    private ObjectMapper jsonMapper = new ObjectMapper();
    private final ProductService productService;
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable(value="id") int id) {
        var product = productService.getProduct(id);
        return new ResponseEntity<>(product, HttpStatusCode.valueOf(HttpStatusCodes.OK));
    }
    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        var product = productService.getProducts();
        return new ResponseEntity<>(product, HttpStatusCode.valueOf(HttpStatusCodes.OK));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteProduct(@PathVariable(value="id") int id) {
        productService.deleteProduct(id);
        return new ResponseEntity(HttpStatusCode.valueOf(HttpStatusCodes.OK));
    }
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody String body) throws JsonProcessingException {
        var dto = jsonMapper.readValue(body, ProductDto.class);
        var createdPproduct = productService.createProduct(dto);
        return new ResponseEntity<>(createdPproduct, HttpStatusCode.valueOf(HttpStatusCodes.CREATED));
    }
    @PatchMapping
    public ResponseEntity updateProduct(@RequestBody String body) throws JsonProcessingException {
        var dto = jsonMapper.readValue(body, ProductDto.class);
        productService.updateProduct(dto);
        return new ResponseEntity(HttpStatusCode.valueOf(HttpStatusCodes.OK));
    }
}
