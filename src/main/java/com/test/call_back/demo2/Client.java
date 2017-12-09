package com.test.call_back.demo2;

/**
 * Created by vivi on 2017/12/10.
 */
public class Client {
    public static void main(String[] args) {
        new Container().registListener(new ContainerListener() {
            @Override
            public void whenStart() {
                System.out.println("容器启动了！！我监听到了");
            }
        }).start();
    }
}
