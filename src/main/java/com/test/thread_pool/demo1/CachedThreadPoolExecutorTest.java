package com.test.thread_pool.demo1;

import com.test.day_171201.demo1.MyThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
 */
public class CachedThreadPoolExecutorTest {

    static class MyThread implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"处理业务逻辑去");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {

            }
            System.out.println("处理业务逻辑完");
        }
    }

    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i=0;i<10;i++){
            try {
                TimeUnit.SECONDS.sleep(1*i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MyThread myThread = new MyThread();
            cachedThreadPool.execute(myThread);
        }
    }
}