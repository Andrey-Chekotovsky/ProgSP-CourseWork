package org.service.Mappers;

import lombok.RequiredArgsConstructor;
import org.db.Models.CompanyUserRelations;
import org.service.Dto.CompanyUserRelationsDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyUserRelationsMapper implements Mapper<CompanyUserRelations, CompanyUserRelationsDto>{
    @Override
    public CompanyUserRelationsDto map(CompanyUserRelations source) {
        return CompanyUserRelationsDto.builder()
                .companyId(source.getCompanyUserId().getCompanyId())
                .userId(source.getCompanyUserId().getUserId())
                .build();
    }
}
