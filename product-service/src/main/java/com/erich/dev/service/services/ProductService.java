package com.erich.dev.service.services;

import com.erich.dev.service.dto.ProductDto;
import com.erich.dev.service.entity.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface ProductService {

     Mono<ProductDto> createProduct(Mono<ProductDto> productDto);

     Mono<ProductDto> fetchProduct(String id);

     Flux<ProductDto> fetchAll();

     Mono<ProductDto> updateProduct(String id, Mono<ProductDto>  productDto);

     Mono<Void> deleteProduct(String id);

     Flux<Product> rangePrice(BigDecimal min, BigDecimal max);

}
