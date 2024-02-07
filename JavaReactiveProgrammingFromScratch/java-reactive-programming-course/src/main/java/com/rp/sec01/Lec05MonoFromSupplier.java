package com.rp.sec01;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class Lec05MonoFromSupplier {

    public static void main(String[] args) {

        // use just only when you have data already
       // Mono<String> mono = Mono.just(getName());

        //If you need to calculate something use supplier
        //It will just be invoked when you suscribe
        //It is goo because you keep things lazy
        Supplier<String> stringSupplier = () -> getName();
        Mono<String> mono = Mono.fromSupplier(stringSupplier);
        mono.subscribe(
                Util.onNext()
        );

        Callable<String> stringCallable = () -> getName();
        Mono.fromCallable(stringCallable)
                .subscribe(
                        Util.onNext()
                );


    }

    private static String getName(){
        System.out.println("Generating name..");
        return Util.faker().name().fullName();
    }

}
