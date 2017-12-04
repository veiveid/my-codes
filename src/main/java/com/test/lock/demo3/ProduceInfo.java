package com.test.lock.demo3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 模拟并发，生产线程在生产的时候，消费线程不可以消费，消费线程在消费的时候，生产线程不可以生产
 */
public class ProduceInfo {

    private String author;
    private String bookName;
    //是否开始生产的标志
    private boolean proFlag;

    //Lock锁
    private Lock lock = new ReentrantLock();
    //condition 变量
    private Condition condition = lock.newCondition();

    public ProduceInfo() {
    }

    public ProduceInfo(String author, String bookName) {
        this.author = author;
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public boolean isProFlag() {
        return proFlag;
    }

    public void setProFlag(boolean proFlag) {
        this.proFlag = proFlag;
    }

    /**
     * 生产者执行的生产方法
     * @param author
     * @param bookName
     * @throws InterruptedException
     */
    public void set(String author,String bookName) throws InterruptedException {
        lock.lock();
        try {
            while(!proFlag){
                condition.await();
            }
            //开始生产
            this.setAuthor(author);
            System.out.println("生产者"+Thread.currentThread().getName()+author+":"+bookName);
            this.setBookName(bookName);
            TimeUnit.SECONDS.sleep(1);
            //生产完毕
            proFlag = false;
            //通知消费者
            condition.signal();

        }finally {
            lock.unlock();
        }
    }

    //消费方法
    public ProduceInfo get() throws InterruptedException {
        lock.lock();
        try {
            while (proFlag){
                condition.await();
            }

            //可以消费了
            //如果没有在生产就就可以取数据
            System.out.println("消费者"+Thread.currentThread().getName()+"---->"+this.getAuthor()+":"+this.getBookName());
            TimeUnit.SECONDS.sleep(1);
            //表示我已经取了数据，生产者可以继续生产
            //消费完毕
            proFlag = true;
            ////通知生产者
            condition.signal();
        }finally {
            lock.unlock();
        }
        return this;
    }
}