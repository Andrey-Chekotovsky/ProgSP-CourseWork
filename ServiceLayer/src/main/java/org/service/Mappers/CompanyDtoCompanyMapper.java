package org.service.Mappers;

import org.db.Models.Company;
import org.service.Dto.CompanyDto;
import org.springframework.stereotype.Component;

@Component
public class CompanyDtoCompanyMapper implements Mapper<CompanyDto, Company>{
    @Override
    public Company map(CompanyDto source) {
        return Company.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .build();
    }
}
