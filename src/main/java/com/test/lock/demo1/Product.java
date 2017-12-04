package com.test.lock.demo1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Product {
    private String name;
    private int count;
    private boolean flag;

    //创建一把锁
    private Lock lock = new ReentrantLock();

    //得到和锁绑定的Condition对象，控制生产线程的唤醒和等待
    private Condition proCon = lock.newCondition();
    //得到和锁绑定的Condition对象，控制消费线程的唤醒和等待
    private Condition cusCon = lock.newCondition();


    //生产产品的功能
    public void produce(String name){
        lock.lock();//获取锁
        try {
            while (flag){
                try {
                    proCon.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                this.name = name+"..."+count;
                System.out.println(Thread.currentThread().getName()+"***生产了***"+this.name);
                count++;
                flag=true;
                cusCon.signal();
            }
        }finally {//为了保证锁必须被释放掉，使用try{...}finally{...}
            lock.unlock();//释放锁
        }
    }

    //消费产品的功能
    public void consume(){
        lock.lock();
        try {
            while (!flag){
                try {
                    cusCon.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"---消费了---"+this.name);
                flag = false;
                proCon.signal();
            }
        }finally {
            lock.unlock();
        }
    }
}