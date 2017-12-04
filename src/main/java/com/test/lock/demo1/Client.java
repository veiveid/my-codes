package com.test.lock.demo1;

public class Client {
    public static void main(String[] args) {
        Product product = new Product();
        Producer producer = new Producer(product);
        Consumer consumer = new Consumer(product);

        new Thread(producer,"生产线01").start();
        new Thread(consumer,"消费者01").start();
        new Thread(producer,"生产线02").start();
        new Thread(consumer,"消费者02").start();
    }
}