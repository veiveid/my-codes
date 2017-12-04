package com.test.design_pattern.chain_of_responsibility.demo1;

import com.test.design_pattern.chain_of_responsibility.demo1.impl.DeptManager;
import com.test.design_pattern.chain_of_responsibility.demo1.impl.GeneralManager;
import com.test.design_pattern.chain_of_responsibility.demo1.impl.ProjectManager;

public class Client {

    public static void main(String[] args) {
        Handler h1 = new ProjectManager();
        Handler h2 = new DeptManager();
        Handler h3 = new GeneralManager();
        h1.setSuccessor(h2);
        h2.setSuccessor(h3);

        //开始测试
        String test1 = h1.handleFeeRequest("张三", 300);
        System.out.println("test1 = " + test1);
        String test2 = h1.handleFeeRequest("李四", 300);
        System.out.println("test2 = " + test2);
        System.out.println("---------------------------------------");

        String test3 = h1.handleFeeRequest("张三", 700);
        System.out.println("test3 = " + test3);
        String test4 = h1.handleFeeRequest("李四", 700);
        System.out.println("test4 = " + test4);
        System.out.println("---------------------------------------");

        String test5 = h1.handleFeeRequest("张三", 1500);
        System.out.println("test5 = " + test5);
        String test6 = h1.handleFeeRequest("李四", 1500);
        System.out.println("test6 = " + test6);
    }
}