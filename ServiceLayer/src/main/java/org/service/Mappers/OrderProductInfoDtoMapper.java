package org.service.Mappers;

import lombok.RequiredArgsConstructor;
import org.db.Models.OrderProductId;
import org.db.Models.OrderProductInfo;
import org.db.Models.Product;
import org.service.Dto.OrderProductInfoDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderProductInfoDtoMapper implements Mapper<OrderProductInfoDto , OrderProductInfo>{
    @Override
    public OrderProductInfo map(OrderProductInfoDto source) {
        return OrderProductInfo.builder()
                .orderProductId(new OrderProductId(
                        source.getOrderId(),
                        Product.builder()
                                .id(source.getProductId())
                                .build()
                ))
                .price(source.getPrice())
                .num(source.getNum())
                .build();
    }
}
