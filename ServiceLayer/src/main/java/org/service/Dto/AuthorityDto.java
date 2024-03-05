package org.service.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.db.Models.Role;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AuthorityDto {
    private Role role;
    private int userId;
    private int companyId = 0;
}
