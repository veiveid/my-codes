package com.test.thread_pool.demo1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolExecutorTest {
    private static final int COUNT = 10;

    public static void main(String[] args) {

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(COUNT);

        for (int i=0;i<3;i++){
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName()+"："+index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }
}