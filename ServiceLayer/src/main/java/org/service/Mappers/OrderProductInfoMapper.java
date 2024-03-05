package org.service.Mappers;

import lombok.RequiredArgsConstructor;
import org.db.Models.OrderProductInfo;
import org.service.Dto.OrderProductInfoDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderProductInfoMapper implements Mapper<OrderProductInfo, OrderProductInfoDto>{

    @Override
    public OrderProductInfoDto map(OrderProductInfo source) {
        return OrderProductInfoDto.builder()
                .num(source.getNum())
                .orderId(source.getOrderProductId().getOrderId())
                .productId(source.getOrderProductId().getProduct().getId())
                .price(source.getPrice())
                .productName(source.getOrderProductId().getProduct().getName())
                .build();
    }
}
