package com.test.day_171123.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class NetworkHealthChecker extends BaseHealthChecker{

    public NetworkHealthChecker(CountDownLatch _latch) {
        super(_latch,"Network Service");
    }

    @Override
    public void verifyService() {
        System.out.println("checking " + this.getServiceName());
        try{
            Thread.sleep(7000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + "is up");
    }
}