package com.test.day_171128.atomic;

public class TestAtomicInteger {
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();

        Thread t1 = new Thread(myThread);
        Thread t2 = new Thread(myThread);

        t1.start();
        t2.start();

        Thread.sleep(500);
        System.out.println(myThread.ai.get());

    }
}