package com.test.day_171130.demo2;

public class TestString {

    /**
     * 这个方法可能应该加入Java标准库。
     * @param s
     * @return
     */
    public static String reverse(String s){
        return new StringBuilder(s).reverse().toString();
    }

    public static void main(String[] args) {
        String s = "1234354546578";
        System.out.println(TestString.reverse(s));
    }
}