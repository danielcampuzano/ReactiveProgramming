package com.campu.webfluxdemo.service;

import com.campu.webfluxdemo.dto.MultiplyRequestDto;
import com.campu.webfluxdemo.dto.ResponseDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ReactiveMathService {

    public Mono<ResponseDto> findSquare(int input) {
        //Mono.just if you were to return only input, but you have to do some operations
        return Mono.fromSupplier(() -> input * input)
                .map(value -> new ResponseDto(value));
    }

    public Flux<ResponseDto> multiplicationTable(int input){
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                //.doOnNext(i -> SleepUtil.sleepSeconds(1))
                .doOnNext(i -> System.out.println("reactive-math-service processing "+i))
                .map(i -> new ResponseDto(i * input));

    }

    public Mono<ResponseDto> multiply(Mono<MultiplyRequestDto> dtoMono){
        return dtoMono
                .map(dto -> dto.getFirst() * dto.getSecond())
                .map(ResponseDto::new);
    }

}
