package com.test.lock.dead_lock;

public class DeadLock {
    public static void main(String[] args) {
        System.out.println(" start the example ----- ");
        final Object obj_1 = new Object(), obj_2 = new Object();

        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                synchronized (obj_1) {
                    try {
                        System.out.println("thread t1 start...");
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {e.printStackTrace();}

                    synchronized (obj_2) {
                        System.out.println("thread t1 done....");
                    }
                }
            }
        };

        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                synchronized (obj_2) {
                    try {
                        System.out.println("thread t2 start...");
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {e.printStackTrace();}

                    synchronized (obj_1) {
                        System.out.println("thread t2 done...");
                    }
                }
            }
        };

        t1.start();
        t2.start();
    }
}