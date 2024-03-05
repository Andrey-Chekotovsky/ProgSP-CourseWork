package org.service.Mappers;

import lombok.RequiredArgsConstructor;
import org.db.Models.Order;
import org.service.Dto.OrderDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderDtoOrderMapper implements Mapper<OrderDto, Order> {
    @Override
    public Order map(OrderDto source) {
        return Order.builder()
                .user(source.getUserId())
                .price(source.getPrice())
                .build();
    }
}
