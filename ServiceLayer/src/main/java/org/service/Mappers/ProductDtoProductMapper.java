package org.service.Mappers;

import lombok.RequiredArgsConstructor;
import org.db.Models.Product;
import org.service.Dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductDtoProductMapper implements Mapper<ProductDto, Product>{
    @Override
    public Product map(ProductDto source) {
        return Product.builder()
                .id(source.getId())
                .companyId(source.getCompanyId())
                .name(source.getName())
                .description(source.getDescription())
                .price(source.getPrice())
                .build();
    }
}
