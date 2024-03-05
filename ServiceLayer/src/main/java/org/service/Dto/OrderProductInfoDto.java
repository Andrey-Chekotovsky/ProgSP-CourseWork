package org.service.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderProductInfoDto {
    private int orderId;
    private int productId;
    private String productName;
    private float price;
    private int num;
}
