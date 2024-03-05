package org.service.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.db.Models.Company;
import org.db.Models.Role;

import java.util.List;

import static org.db.Models.Role.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDto {
    private int id;
    private String username;
    private String password;
    private Role role;

    private boolean banned = false;
    private List<String> subscriptions;
    private List<OrderDto> orders;
    private CompanyDto company;
}
