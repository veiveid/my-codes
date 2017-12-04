package com.test.lock.demo1;

public class Producer implements Runnable {
    private Product pro;

    public Producer(Product pro) {
        this.pro = pro;
    }

    @Override
    public void run() {
        while (true){
            pro.produce("笔记本电脑");
        }
    }
}