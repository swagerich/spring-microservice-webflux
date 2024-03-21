package com.erich.dev.service.services.impl;

import com.erich.dev.service.dto.ProductDto;

import com.erich.dev.service.entity.Product;
import com.erich.dev.service.exception.NotFoundException;
import com.erich.dev.service.repository.ProductRepository;
import com.erich.dev.service.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Mono<ProductDto> createProduct(Mono<ProductDto> productDto) {
        return productDto.map(ProductDto::toEntity)
                .flatMap(productRepository::insert)
                .map(ProductDto::fromEntity);
    }

    @Override
    public Flux<ProductDto> fetchAll() {
        return productRepository.findAll().map(ProductDto::fromEntity);
    }

    @Override
    public Mono<ProductDto> fetchProduct(String id) {
        return productRepository.findById(id).switchIfEmpty(Mono.error(new NotFoundException("Prduct Not Found"))).map(ProductDto::fromEntity);
    }

    @Override
    public Mono<ProductDto> updateProduct(String id, Mono<ProductDto> productDto) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Product not found")))
                .flatMap(p -> productDto.map(ProductDto::toEntity)
                        .doOnNext(e -> {
                            e.setId(p.getId());
                        }))
                .flatMap(productRepository::save)
                .map(ProductDto::fromEntity);
    }

    @Override
    public Mono<Void> deleteProduct(String id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Product not found")))
                .flatMap(productRepository::delete);
    }

    @Override
    public Flux<Product> rangePrice(BigDecimal min, BigDecimal max) {
        return productRepository.findByPriceBetween(Range.closed(min, max));
    }
}
