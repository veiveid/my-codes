package com.test.lambda.demo1;

public class RunnableTest {
    public static void main(String[] args) {
        new Thread(() -> System.out.println("In Java8, Lambda expression rocks !!")).start();
    }
}