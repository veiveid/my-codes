package com.test.day_171123.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class DatabaseHealthChecker extends BaseHealthChecker {
    public DatabaseHealthChecker(CountDownLatch _latch) {
        super(_latch, "Database Service");
    }

    @Override
    public void verifyService() {
        System.out.println("checking" + this.getServiceName());
        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + "is up");
    }
}