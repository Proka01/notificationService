package com.raf.restdemo.mapper;

import com.raf.restdemo.domain.Product;
import com.raf.restdemo.dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDto productToProductDto(Product product) {
        return ProductDto.builder()
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .id(product.getId())
                .build();
    }

    public Product productDtoToProduct(ProductDto productDto) {
        return Product.builder()
                .title(productDto.getTitle())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .id(productDto.getId())
                .build();
    }

}
