package com.test.design_pattern.proxy.demo1;

interface Network{
    void browse();
}

class Realnet implements Network{

    @Override
    public void browse() {
        System.out.println("上网浏览");
    }
}

class NetProxy implements Network{//代理类。

    private Network network;// 代理对象

    public NetProxy(Network network) {//初始化，把真实对象传给代理对象，向上转型操作。
        this.network = network;
    }

    @Override
    public void browse() {
        this.check();
        this.network.browse();
    }

    public void check(){// 检查用户合法性
        System.out.println("检查用户合法性");
    }
}

public class ProxyDemo {
    public static void main(String[] args) {
        new NetProxy(new Realnet()).browse();//  指定代理操作，这里两次向上转型操作，第一次向上是实例化代理类，
        //第二次向上转型是括号中，把真实类对象传入，以便在代理类中调用真实类中的方法。

    }
}