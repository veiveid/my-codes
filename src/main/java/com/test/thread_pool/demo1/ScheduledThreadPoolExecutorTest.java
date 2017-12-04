package com.test.thread_pool.demo1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 创建一个定长线程池，支持定时及周期性任务执行。
 */
public class ScheduledThreadPoolExecutorTest {

    //延迟执行示例代码如下：
    public static void delay(){
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 3 seconds");
            }
        },3, TimeUnit.SECONDS);
    }

    //定期执行示例代码如下：
    public static void timing(){
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 1 seconds, and excute every 3 seconds");
            }
        },1,3,TimeUnit.SECONDS);//表示延迟1秒后每3秒执行一次。
    }

    public static void main(String[] args) {
        //ScheduledThreadPoolExecutorTest.timing();
        ScheduledThreadPoolExecutorTest.delay();
    }
}