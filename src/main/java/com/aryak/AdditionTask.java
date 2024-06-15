package com.aryak;

import java.util.Random;
import java.util.concurrent.Callable;

public class AdditionTask implements Callable<Integer> {

    Random random = new Random();

    @Override
    public Integer call() throws Exception {

        var i = random.nextInt(10);
        var j = random.nextInt(10);
        System.out.println("i = " + i + " j = " + j + " product = " + i * j);
        return i * j;
    }
}
