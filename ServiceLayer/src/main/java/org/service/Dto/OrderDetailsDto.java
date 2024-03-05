package org.service.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.db.Models.OrderProductInfo;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDto {
    private int id;
    private int userId;
    private float price;
    private List<OrderProductInfoDto> details;
    public OrderDetailsDto(OrderDto dto, List<OrderProductInfoDto> details) {
        id = dto.getId();
        userId = dto.getUserId();
        price = dto.getPrice();
        this.details = details;
    }
}
