package com.test.lock.demo3;

import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        ProduceInfo produceInfo = new ProduceInfo();
        produceInfo.setProFlag(true);

        Producer producer = new Producer(produceInfo);
        Consumer consumer = new Consumer(produceInfo);

        new Thread(producer,"Producer").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(consumer,"Consumer").start();
    }
}