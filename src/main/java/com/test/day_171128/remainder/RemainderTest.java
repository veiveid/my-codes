package com.test.day_171128.remainder;

import java.util.Random;

public class RemainderTest {

    public static void main(String[] args) {
        Random random = new Random();
        for(int i=0;i<10000;i++){
            int n = random.nextInt(10000);
            System.out.println("随机数="+n+"，对1取余="+n%1);
            System.out.println("随机数="+n+"，对2取余="+n%2);
            System.out.println("随机数="+n+"，对3取余="+n%3);
            System.out.println("随机数="+n+"，对4取余="+n%4);
            System.out.println("随机数="+n+"，对5取余="+n%5);
            System.out.println("随机数="+n+"，对6取余="+n%6);
            System.out.println("随机数="+n+"，对7取余="+n%7);
            System.out.println("随机数="+n+"，对8取余="+n%8);
            System.out.println("随机数="+n+"，对9取余="+n%9);
            System.out.println("随机数="+n+"，对10取余="+n%10);
        }
    }
}