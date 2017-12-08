package com.test.thread.callable.demo1;

import java.util.concurrent.*;

class FindMaxTask implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        Thread.sleep(5000);//模拟处理时间
        return 0;
    }
}

public class GetMaxFinder {

    public static void main(String[] args) {
        try {
            ExecutorService service = Executors.newFixedThreadPool(2);

            //<T> Future<T> submit(Callable<T> task);
            Future<Integer> future = service.submit(new FindMaxTask());

            System.out.println("任务已经提交");
            Integer result = future.get();//get()方法用来获取执行结果，该方法会阻塞直到任务返回结果。
            System.out.println(result);

        } catch (InterruptedException e) {

        } catch (ExecutionException e) {

        }
    }
}