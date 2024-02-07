package com.rp.sec02;

import com.rp.courseutil.Util;
import com.rp.sec02.helper.NameGenerator;

public class Lec07FluxVsList {

    public static void main(String[] args) {

       // List<String> names = NameGenerator.getNames(5);
        //System.out.println(names);

        //It is better than the above lines with list because it gives me one name each one second
        //As soon as the publisher is ready it gives me one item
        //With list you have to wait 5 seconds and then you get all the results
        NameGenerator.getNames(5)
                .subscribe(Util.onNext());



    }

}
