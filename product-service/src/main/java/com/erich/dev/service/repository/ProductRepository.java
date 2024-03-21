package com.erich.dev.service.repository;

import com.erich.dev.service.entity.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.io.File;
import java.math.BigDecimal;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {



//    Flux<Product> findByPriceBetween(BigDecimal min, BigDecimal max);
    Flux<Product> findByPriceBetween(Range<BigDecimal> range);
}
