package com.erich.dev.service.dto;


import com.erich.dev.service.entity.Product;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductDto {

    private String name;

    private BigDecimal price;


    public static ProductDto fromEntity(Product product) {
        if(product == null){
            return null;
        }
        return ProductDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }

    public static Product toEntity(ProductDto productDto) {
        if(productDto == null){
            return null;
        }
        return Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .build();
    }
}
