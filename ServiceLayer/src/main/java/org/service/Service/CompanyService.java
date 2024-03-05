package org.service.Service;

import lombok.RequiredArgsConstructor;
import org.db.JpaRepositories.CompanyRepository;
import org.db.Models.Company;
import org.service.Dto.CompanyDto;
import org.service.Mappers.Mapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final Mapper<Company, CompanyDto> companyCompanyDtoMapper;
    private final Mapper<CompanyDto, Company> companyDtoCompanyMapper;

    public List<CompanyDto> getCompanies() {
        var companies = companyRepository.findAll();
        List<CompanyDto> companyDtos = new ArrayList<>();
        companies.stream().forEach(company -> {
            companyDtos.add(companyCompanyDtoMapper.map(company));
        });
        return companyDtos;
    }
    public CompanyDto getCompany(int id) {
        return companyCompanyDtoMapper.map(
                companyRepository.getReferenceById(id)
        );
    }
    public void deleteCompany(int id) {
        companyRepository.deleteById(id);
    }
    public void updateCompany(CompanyDto companyDto) {
        var company = companyDtoCompanyMapper.map(companyDto);
        companyRepository.save(company);
    }
    public CompanyDto createCompany(CompanyDto companyDto) {
        var company = companyDtoCompanyMapper.map(companyDto);
        return companyCompanyDtoMapper.map(
                companyRepository.save(company)
        );
    }

}
