package com.example.chekotovsky.Controllers;

import com.example.chekotovsky.Constants.HttpStatusCodes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.db.Models.Company;
import org.service.Dto.CompanyDto;
import org.service.Dto.CompanyUserRelationsDto;
import org.service.Service.CompanyService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/companies")
public class CompanyController {
    private ObjectMapper jsonMapper = new ObjectMapper();
    private final CompanyService companyService;
    @GetMapping
    public ResponseEntity<List<CompanyDto>> getCompanies() {
        var companies = companyService.getCompanies();
        return new ResponseEntity<>(companies, HttpStatusCode.valueOf(HttpStatusCodes.OK));
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<CompanyDto> getCompany(@PathVariable(value="id") int id) {
        var company = companyService.getCompany(id);
        return new ResponseEntity<>(company, HttpStatusCode.valueOf(HttpStatusCodes.OK));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteCompany(@PathVariable(value="id") int id) {
        companyService.deleteCompany(id);
        return new ResponseEntity(HttpStatusCode.valueOf(HttpStatusCodes.OK));
    }
    @PostMapping
    public ResponseEntity<CompanyDto> createCompany(@RequestBody String body) throws JsonProcessingException {
        var dto = jsonMapper.readValue(body, CompanyDto.class);
        var createdCompany = companyService.createCompany(dto);
        return new ResponseEntity<>(createdCompany, HttpStatusCode.valueOf(HttpStatusCodes.CREATED));
    }
    @PatchMapping
    public ResponseEntity updateCompany(@RequestBody String body) throws JsonProcessingException {
        var dto = jsonMapper.readValue(body, CompanyDto.class);
        companyService.updateCompany(dto);
        return new ResponseEntity(HttpStatusCode.valueOf(HttpStatusCodes.OK));
    }

}
