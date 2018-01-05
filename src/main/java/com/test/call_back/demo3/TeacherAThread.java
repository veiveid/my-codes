package com.test.call_back.demo3;

public class TeacherAThread implements Runnable {

    private Callback callback;
    private Student student;

    public TeacherAThread(Callback callback, Student student) {
        this.callback = callback;
        this.student = student;
    }

    @Override
    public void run() {
        student.resolveQuestion(callback);
    }
}