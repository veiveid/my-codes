package com.test.abstract_class.demo1;

/**
 * 接口在实际应用中更多的作用是来指定标准的。比如说”U盘和打印机都可以插在电脑上使用，因为他们都实现了USB标准的接口，
 * 对于电脑来说，只要符合USB接口标准的设备都可以插进来。
 */
interface USB{// 定义了USB接口
    void start();
    void stop();
}

class Computer{
    public void plugin(USB usb){
        usb.start();
        System.out.println("=========== USB 设备工作 ========");
        usb.stop();
    }
}

class Printer implements USB{

    @Override
    public void start() { // 覆写方法
        System.out.println("打印机开始工作。") ;
    }

    @Override
    public void stop() { // 覆写方法
        System.out.println("打印机停止工作。") ;
    }
}

class Flash implements USB{

    @Override
    public void start() { // 覆写方法
        System.out.println("U盘开始工作。") ;
    }

    @Override
    public void stop() { // 覆写方法
        System.out.println("U盘停止工作。") ;
    }
}

public class InterfaceDemo {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.plugin(new Printer());
        computer.plugin(new Flash());
    }
}