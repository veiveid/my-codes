package com.test.lock.demo3;

import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {
    private ProduceInfo produceInfo;

    public Consumer(ProduceInfo produceInfo) {
        this.produceInfo = produceInfo;
    }

    @Override
    public void run() {
        try {
            while (true){
                ProduceInfo p = produceInfo.get();
                TimeUnit.SECONDS.sleep(5);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}