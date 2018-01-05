package com.test.call_back.demo3;

/**
 * 回调测试
 */
public class Test {

    public static void main(String[] args) {
        StudentA student = new StudentA("小明");
        TeacherA teacher = new TeacherA(student);

        teacher.askQuestion();
    }
}