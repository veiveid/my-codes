package com.test.call_back.demo3;

public class TeacherA implements Callback {
    private StudentA student;

    public TeacherA(StudentA student) {
        this.student = student;
    }

    //老师提问
    public void askQuestion(){
        System.out.println("老师开始提问"+student.getName()+"....balabala");
        try {
            Thread.sleep(1000);//模拟提问耗时
        } catch (InterruptedException e) {

        }
        new Thread(new TeacherAThread(this,student)).start();//异步线程
        //student.resolveQuestion(this);

        System.out.println("先让"+student.getName()+"同学思考一下，我提问其他同学");
    }

    @Override
    public void receiveAnswer(int answer) {
        System.out.println(student.getName()+"同学的答案是： "+answer);
    }
}