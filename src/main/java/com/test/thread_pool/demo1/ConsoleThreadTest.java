package com.test.thread_pool.demo1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 你可以使用JDK自带的监控工具来监控我们创建的线程数量，运行一个不终止的线程，创建指定量的线程，来观察：
 工具目录：C:\Program Files\Java\jdk1.6.0_06\bin\jconsole.exe
 运行程序做稍微修改：
 */
public class ConsoleThreadTest {
    public static void main(String[] args) {
        ExecutorService singleThreadExecutor = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                public void run() {
                    try {
                        while(true) {
                            System.out.println(index);
                            Thread.sleep(10 * 1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}