package com.campu.productservice.controller;

import com.campu.productservice.dto.ProductDto;
import com.campu.productservice.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("product")
public class ProductController {

    private ProductService service;

    @GetMapping("all")
    public Flux<ProductDto> all(){
        return this.service.getAll();
    }

    @GetMapping("{id}")
    public Mono<ProductDto> getProductById(String id){
        return this.service.getProductById(id);
    }


}
