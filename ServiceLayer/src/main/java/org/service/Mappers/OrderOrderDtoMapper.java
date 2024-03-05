package org.service.Mappers;


import lombok.RequiredArgsConstructor;
import org.db.Models.Order;
import org.db.Models.Product;
import org.service.Dto.OrderDto;
import org.service.Dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderOrderDtoMapper implements Mapper<Order, OrderDto> {
    private final Mapper<Product, ProductDto> productProductDtoMapper;
    public OrderDto map(Order order) {
        return new OrderDto().toBuilder()
                .id(order.getId())
                .userId(order.getId())
                .price(order.getPrice())
                .build();
    }
}
