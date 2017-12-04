package com.test.day_171201.demo1;

import java.util.concurrent.TimeUnit;

public class TestAtomicInteger {
    public static void main(String[] args) throws InterruptedException {
        Bucket bucket = new Bucket();
        MyThread myThread = new MyThread(bucket);

        Thread t1 = new Thread(myThread);
        Thread t2 = new Thread(myThread);
        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(bucket.getCount());
    }
}