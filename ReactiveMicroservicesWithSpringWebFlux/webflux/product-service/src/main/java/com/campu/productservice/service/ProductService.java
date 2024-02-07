package com.campu.productservice.service;

import com.campu.productservice.dto.ProductDto;
import com.campu.productservice.repository.ProductRepository;
import com.campu.productservice.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Flux<ProductDto> getAll() {
        return this.productRepository.findAll()
                .map(EntityDtoUtil::toDto);
    }

    public Mono<ProductDto> getProductById(String id) {
        return this.productRepository.findById(id)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<ProductDto> insertProduct(Mono<ProductDto> productDtoMono){
        return productDtoMono
                .map(productDto -> EntityDtoUtil.toEntity(productDto))
                .flatMap(productEntity -> this.productRepository.insert(productEntity))
                .map(productEntity -> EntityDtoUtil.toDto(productEntity));

    }

    public Mono<ProductDto> updateProduct(String id, Mono<ProductDto> productDtoMono){
        return this.productRepository.findById(id)
                .flatMap(productEntity -> productDtoMono
                                        .map(productDto -> EntityDtoUtil.toEntity(productDto))
                                        .doOnNext(productEnt -> productEntity.setId(id)))
                .flatMap(productEntity -> this.productRepository.save(productEntity))
                .map(productEntity -> EntityDtoUtil.toDto(productEntity));
    }

    public Mono<Void> deleteProduct(String id){
        return this.productRepository.deleteById(id);
    }

}
