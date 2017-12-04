package com.test.lock.demo1;

public class Consumer implements Runnable {
    private Product pro;

    public Consumer(Product pro) {
        this.pro = pro;
    }

    @Override
    public void run() {
        while (true){
            pro.consume();
        }
    }
}