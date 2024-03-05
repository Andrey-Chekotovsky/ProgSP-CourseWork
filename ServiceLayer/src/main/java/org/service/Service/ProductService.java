package org.service.Service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.db.JpaRepositories.ProductRepository;
import org.db.Models.Order;
import org.db.Models.Product;
import org.service.Dto.ProductDto;
import org.service.Mappers.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final Mapper<Product, ProductDto> productProductDtoMapper;
    private final Mapper<ProductDto, Product> productDtoProductMapper;

    public ProductDto getProduct(int id) {
        return productProductDtoMapper.map(productRepository.getReferenceById(id));
    }
    public List<ProductDto> getProducts() {
        var products = productRepository.findAll();
        var dtos = products.stream().map(productProductDtoMapper::map).toList();
        return dtos;
    }
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
    public void updateProduct(ProductDto dto) {
        var product = productDtoProductMapper.map(dto);
        productRepository.save(product);
    }
    public ProductDto createProduct(ProductDto product) {
        return productProductDtoMapper.map(
                productRepository.save(
                    productDtoProductMapper.map(product)
            )
        );
    }
}
