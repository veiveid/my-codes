package com.test.thread_local.demo1;

public class TestThreadLocal {

    ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    public void test1(){
        String str = "123";
        threadLocal.set(str);
        str = null;
        this.test2();
    }

    public void test2(){
        System.out.println(threadLocal.get());
    }

    public static void main(String[] args) {
        TestThreadLocal t = new TestThreadLocal();
        t.test1();
    }
}