package com.erich.dev.service.controller;

import com.erich.dev.service.dto.ProductDto;
import com.erich.dev.service.entity.Product;
import com.erich.dev.service.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public Flux<ProductDto> findAll(){
        return productService.fetchAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ProductDto>> findById(@PathVariable String id){
        return productService.fetchProduct(id).map(ResponseEntity::ok);
    }

    @PostMapping
    public Mono<ResponseEntity<ProductDto>> createProduct(@RequestBody Mono<ProductDto> productDto){
         return productService.createProduct(productDto)
                 .map(p -> ResponseEntity.status(HttpStatus.CREATED).body(p));

    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ProductDto>> updateProduct(@PathVariable String id, @RequestBody Mono<ProductDto> productDto){
        return productService.updateProduct(id, productDto)
                .map(p -> ResponseEntity.status(HttpStatus.ACCEPTED).body(p));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteProduct(@PathVariable String id){
        return productService.deleteProduct(id).then(Mono.just(new ResponseEntity<>(HttpStatus.NO_CONTENT)));
    }

    @GetMapping("/range")
    public Flux<Product> rangePrice(@RequestParam("min") BigDecimal min, @RequestParam("max") BigDecimal max){
        return productService.rangePrice(min, max);
    }
}
