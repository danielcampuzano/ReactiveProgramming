package com.campu.webfluxdemo.controller;

import com.campu.webfluxdemo.dto.MultiplyRequestDto;
import com.campu.webfluxdemo.dto.ResponseDto;
import com.campu.webfluxdemo.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("reactive-math")
public class ReactiveMathController {

    @Autowired
    private ReactiveMathService mathService;

    @GetMapping("square/{input}")
    public Mono<ResponseDto> findSquare(@PathVariable int input) {
        return this.mathService.findSquare(input);
    }

    @GetMapping("table/{input}")
    public Flux<ResponseDto> multiplicationTable(@PathVariable int input){
        return this.mathService.multiplicationTable(input);
    }

    @GetMapping(value = "table/{input}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ResponseDto> multiplicationTableStream(@PathVariable int input){
        return this.mathService.multiplicationTable(input);
    }

    @PostMapping("multiply")
    public Mono<ResponseDto> multiply(@RequestBody Mono<MultiplyRequestDto> multiplyRequestDtoMono){
        return this.mathService.multiply(multiplyRequestDtoMono);
    }

}
