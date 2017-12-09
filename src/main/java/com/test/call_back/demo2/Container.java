package com.test.call_back.demo2;

/**
 * Created by vivi on 2017/12/10.
 * 容器类，有个启动方法start
 */
public class Container {

    private ContainerListener listener;

    public Container() {

    }

    public Container registListener(ContainerListener listener){
        this.listener = listener;
        return this;
    }

    public void start(){
        if (null != listener)
            listener.whenStart();//启动的时候注册监听
        System.out.println("容器正在做它自己的事情");
        System.out.println("容器做完了自己的事情");
    }
}
