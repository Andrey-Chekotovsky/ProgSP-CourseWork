package org.service.Mappers;

import org.db.Models.Company;
import org.db.Models.Message;
import org.db.Models.User;
import org.service.Dto.CompanyDto;
import org.service.Dto.MessageDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CompanyCompanyDtoMapper implements Mapper<Company, CompanyDto>{
    @Override
    public CompanyDto map(Company source) {
        return CompanyDto.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .build();
    }
}
