package com.test.day_171123.countDownLatch3;

import java.util.concurrent.CountDownLatch;

class DemoThread implements Runnable{

    private CountDownLatch countDownLatch;

    public DemoThread(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();//await方法是线程进入休眠，当countDownLatch计数器为0时，将被唤醒
            System.out.println(Thread.currentThread().getName()+"来了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ManyThreadCountDown {

    public static void main(String[] args) throws InterruptedException {
        int n = 10;
        CountDownLatch countDownLatch = new CountDownLatch(n);
        for (int i=0;i<n;i++){
            try {
                Thread.sleep(1000);//模拟延时
                new Thread(new DemoThread(countDownLatch)).start();
            }finally {
                countDownLatch.countDown();//减计数
            }
        }
    }
}