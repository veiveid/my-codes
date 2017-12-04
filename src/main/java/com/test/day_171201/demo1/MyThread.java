package com.test.day_171201.demo1;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread implements Runnable {
    private Bucket bucket;

    public MyThread(Bucket bucket) {
        this.bucket = bucket;
    }

    @Override
    public void run() {
        for (int i=0;i<10000;i++){
            AtomicInteger count = bucket.getCount();
            count.getAndIncrement();
            bucket.setCount(count);
        }
    }
}