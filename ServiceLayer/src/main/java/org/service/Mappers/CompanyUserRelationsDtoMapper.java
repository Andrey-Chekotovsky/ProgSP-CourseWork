package org.service.Mappers;

import lombok.RequiredArgsConstructor;
import org.db.Models.CompanyUserId;
import org.db.Models.CompanyUserRelations;
import org.service.Dto.CompanyUserRelationsDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyUserRelationsDtoMapper implements Mapper<CompanyUserRelationsDto, CompanyUserRelations> {
    @Override
    public CompanyUserRelations map(CompanyUserRelationsDto source) {
        return CompanyUserRelations.builder()
                .companyUserId(new CompanyUserId(source.getCompanyId(),
                        source.getUserId()))
                .build();
    }
}
