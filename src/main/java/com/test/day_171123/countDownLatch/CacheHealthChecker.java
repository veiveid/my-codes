package com.test.day_171123.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class CacheHealthChecker extends BaseHealthChecker {
    public CacheHealthChecker(CountDownLatch _latch) {
        super(_latch, "Cache Service");
    }

    @Override
    public void verifyService() {
        System.out.println("checking " + this.getServiceName());
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + "is up");
    }
}