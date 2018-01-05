package com.test.call_back.demo3;

public class StudentA implements Student {

    private String name;

    public StudentA(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void resolveQuestion(Callback callback) {
        try {
            //模拟解决问题耗时
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //回调，告诉老师答案
        callback.receiveAnswer(33);
    }
}