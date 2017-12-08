package com.test.fan_xing.demo1;

import java.util.List;

/**
 * 定义带类型参数方法
 */
public class WW {
    public <T> void write(T t,T[] ta){

    }

    public <S> void read(S s ,S[] sa){

    }

    public <T,S extends T> T test(T t,S s){
        S ss = null;
        return ss;
    }

    public <T> void test1(List<T> u){}//1

    public <S> void test2(List<S> u){}//2

    //上面的1、2方法可以综合为下面的写法：
    public void test3(List<?> u){}
}