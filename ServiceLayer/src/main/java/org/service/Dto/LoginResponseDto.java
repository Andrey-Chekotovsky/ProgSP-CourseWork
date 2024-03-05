package org.service.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.db.Models.Company;
import org.db.Models.Role;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private int id;
    private String accessToken;
    private Role role;
    private CompanyDto company;
    private boolean banned;
}
