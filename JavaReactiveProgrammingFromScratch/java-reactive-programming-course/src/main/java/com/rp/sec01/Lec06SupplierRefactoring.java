package com.rp.sec01;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class Lec06SupplierRefactoring {

    public static void main(String[] args) {
        //Just builds the pipeline
        getName();
        String name = getName()
                //It helps to make it non-blocking
                .subscribeOn(Schedulers.boundedElastic())
                //Useful for testing purposes but it shouldnt be done
                //It blocks the threadpool and gives you the result - it internally suscribes
                .block();
        System.out.println(name);
        getName();

        //I have to sleep the main thread in order to get the name obtained in the non-blocking call
        //It shouldnt be necessary, it's just in this example
        Util.sleepSeconds(4);
    }

    //Acts like a publisher
    private static Mono<String> getName(){
        System.out.println("entered getName method");
        return Mono.fromSupplier(() -> {
            System.out.println("Generating name..");
            Util.sleepSeconds(3);
            return Util.faker().name().fullName();
        }).map(String::toUpperCase);
    }


}
