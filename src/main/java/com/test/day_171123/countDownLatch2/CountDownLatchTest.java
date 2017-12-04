package com.test.day_171123.countDownLatch2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *模拟了100米赛跑，10名选手已经准备就绪，只等裁判一声令下。当所有人都到达终点时，比赛结束。
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws Exception{

        // 开始的倒数锁
        final CountDownLatch begin = new CountDownLatch(1);

        // 结束的倒数锁
        final CountDownLatch end = new CountDownLatch(10);

        // 十名选手
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i=0;i<10;i++){
            final int No = i+1;
            Runnable run = new Runnable(){
                @Override
                public void run() {
                    try {
                        begin.await();
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println(No + "号选手到达终点");
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }finally {
                        end.countDown();
                    }
                }
            };
            executorService.submit(run);
        }

        System.out.println("开跑");

        //开跑
        begin.countDown();

        // 等待end变为0，即所有选手到达终点
        end.await();

        System.out.println("Game Over");
        executorService.shutdown();
    }
}