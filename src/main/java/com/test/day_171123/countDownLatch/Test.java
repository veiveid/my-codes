package com.test.day_171123.countDownLatch;

public class Test {

    public static void main(String[] args) {
        boolean result = false;
        try {
            result = ApplicationStartupUtil.checkExternalServices();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("External services validation completed !! Result was :: "+ result);
    }
}