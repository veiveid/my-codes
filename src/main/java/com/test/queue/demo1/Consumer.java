package com.test.queue.demo1;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private BlockingQueue<Integer> queue;
    private volatile boolean flag;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
        //this.flag = false;
    }

    @Override
    public void run() {
        while (!flag){
            int info;
            try {
                info = queue.take();
                System.out.println(Thread.currentThread().getName()+" consumer "+info);
                Thread.sleep(50);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void shutDown(){
        flag = true;
    }
}