package com.test.thread.thread_debug.demo1;

/**
 * idea开发工具：在断点上右键 单选按钮选择Thread 并设置为default,这样每个线程独立执行，互不干扰
 */
public class Test {
    static int n = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread("thread99") { // 断点0
            @Override
            public void run() {
                n+=1;
                System.out.println("1"); // 断点1
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                n+=1;
                System.out.println("2"); // 断点2
            }
        }.start();
        n-=2;
        // 外线程
        System.out.println("3"); // 断点3
        Thread.sleep(2000);
        System.out.println("4"); // 断点4
    }
}