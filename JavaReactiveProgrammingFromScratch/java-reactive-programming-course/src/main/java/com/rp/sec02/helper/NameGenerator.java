package com.rp.sec02.helper;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

public class NameGenerator {

/*    public static List<String> getNames(int count){
        List<String> list = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            list.add(getName());
        }
        return list;
    }*/

    //With flux is better because you dont need to wait N seconds to the result
    //On the other hand, using the list implementation above you have to wait N seconds
    public static Flux<String> getNames(int count){
       return Flux.range(0, count)
                .map(i -> getName());
    }

    private static String getName(){
        Util.sleepSeconds(1);
        return Util.faker().name().fullName();
    }

}
