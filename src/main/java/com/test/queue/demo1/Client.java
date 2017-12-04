package com.test.queue.demo1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Client {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingDeque<Integer>();
        Producer p = new Producer(queue);
        Consumer c = new Consumer(queue);

        for (int i=0;i<10;i++){
            if(i<5){
                new Thread(p).start();
            }else{
                new Thread(c).start();
            }
        }

        try {
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }

        //p.shutdown();
        //c.shutDown();
    }
}