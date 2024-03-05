package org.service.Mappers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.db.Models.Product;
import org.service.Dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductProductDtoMapper implements Mapper<Product, ProductDto> {
    public ProductDto map(Product product) {
        return new ProductDto().toBuilder()
                .id(product.getId())
                .companyId(product.getCompanyId())
                .description(product.getDescription())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}
