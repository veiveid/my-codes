package com.test.queue.demo1;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private BlockingQueue<Integer> queue;
    private volatile boolean flag;
    private Random random;


    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
        flag = false;
        random = new Random();
    }

    @Override
    public void run() {
        while (!flag){
            int info = random.nextInt(100);
            try {
                queue.put(info);
                System.out.println(Thread.currentThread().getName()+"produce:"+info);
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown(){
        flag = true;
    }
}