package com.test.thread.callable.demo1;


import java.util.concurrent.*;

public class CallableAndFuture {

    static class MyThread implements Callable<String>{

        @Override
        public String call() throws Exception {
            return "Hello world";
        }
    }

    public static void main(String[] args) throws Exception{
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<String> res = service.submit(new MyThread());
        service.shutdown();//关闭线程池
        if(null != res.get())
            System.out.println(res.get());

    }
}