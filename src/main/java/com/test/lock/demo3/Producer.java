package com.test.lock.demo3;

import java.util.concurrent.TimeUnit;

public class Producer implements Runnable {
    private ProduceInfo produceInfo;

    public Producer(ProduceInfo produceInfo) {
        this.produceInfo = produceInfo;
    }

    @Override
    public void run() {
        try {
            for(int i=0;i<5;i++){
                String author = "liuCiXin";
                String bookName = "sanTi"+i;
                produceInfo.set(author,bookName);
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}