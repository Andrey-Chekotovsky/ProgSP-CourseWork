package org.service.Mappers;


import lombok.RequiredArgsConstructor;
import org.db.Models.Company;
import org.db.Models.Order;
import org.db.Models.User;
import org.service.Dto.CompanyDto;
import org.service.Dto.OrderDto;
import org.service.Dto.UserDetailsDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@RequiredArgsConstructor
public class UserUserDetailsDtoMapper implements Mapper<User, UserDetailsDto>{
    private final Mapper<Order, OrderDto> orderOrderDtoMapper;
    private final Mapper<Company, CompanyDto> companyCompanyDtoMapper;

    public UserDetailsDto map(User user) {
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : user.getOrders()) {
            orderDtos.add(orderOrderDtoMapper.map(order));
        }
        List<String> subscriptions = new ArrayList<>();
        for (Company company : user.getSubscriptions()) {
            subscriptions.add(company.getName());
        }
        return UserDetailsDto.builder()
                .username(user.getUsername())
                .id(user.getId())
                .banned(user.isBanned())
                .orders(orderDtos)
                .role(user.getRole())
                .subscriptions(subscriptions)
                .company(user.getCompany() != null ? companyCompanyDtoMapper.map(user.getCompany()) : null)
                .build();
    }
}
