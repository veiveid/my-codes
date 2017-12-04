package com.test.day_171128.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread implements Runnable {
//    static  int i = 0;
    static AtomicInteger ai = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i=0;i<1000;i++){
            ai.getAndIncrement();
        }
    }
}