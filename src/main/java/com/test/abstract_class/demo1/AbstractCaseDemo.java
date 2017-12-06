package com.test.abstract_class.demo1;

/**
 * 抽象类的实际应用--模板设计
 * 首先看这样一个场景：假设人分学生和工人，学生和工人都可以说话，但是学生和工人说的话内容不一样，也就是说
   说话这个功能应该是具体功能，而说话的内容就要由学生或工人来决定了。所以此时可以使用抽象类实现这种场景。
 */
abstract class Person{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void say(){ // 人说话是一个具体的功能
        System.out.println(this.getContent());// 输出内容
    }

    protected abstract String getContent();// 说话的内容由子类决定

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

class Student extends Person{
    private float score;

    public Student(String name, int age, float score) {
        super(name, age); // 调用父类中的构造方法
        this.score = score;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    protected String getContent() {
        return "学生信息 --> 姓名："+super.getName()+"；年龄："+super.getAge()
                +"；成绩："+this.getScore();
    }
}

class Worker extends Person{
    private float salary;

    public Worker(String name, int age, float salary) {
        super(name, age);// 调用父类中的构造方法
        this.salary = salary;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    protected String getContent() {
        return "工人信息 --> 姓名：" + super.getName() + "；年龄：" + super.getAge()
                + "；工资：" + this.getSalary();
    }
}

public class AbstractCaseDemo {
    public static void main(String[] args) {
        Person student = new Student("小明",12,99.0F);
        Person worker = new Worker("Jon",23,3000.0F);
        student.say();
        worker.say();
    }
}