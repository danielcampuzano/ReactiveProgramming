package com.rp.sec01;

import reactor.core.publisher.Mono;

public class Lec02MonoJust {

    public static void main(String[] args) {

        // publisher
        //easiest way to create a mono
        Mono<Integer> mono = Mono.just(1);

        System.out.println(mono);

        //I provide the behavior for the OnNext
        mono.subscribe(i -> System.out.println("Received : " + i));

    }

}
