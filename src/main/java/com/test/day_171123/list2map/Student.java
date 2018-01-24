package com.test.day_171123.list2map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Student {

    private String name;
    private int age;
    private Long hight;
    private int sex;

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

    public Long getHight() {
        return hight;
    }

    public void setHight(Long hight) {
        this.hight = hight;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    // 设置 年龄和性别的拼接，得出相应分组
    public Long getIwantStudent(){
        return  Long.valueOf(this.sex + this.age);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hight=" + hight +
                ", sex=" + sex +
                '}';
    }

    public static void main(String[] args) {
        List<Student> allList = new ArrayList<>();
        //添加集合信息 省去。。。
        Student st1 = new Student();
        st1.setAge(20);
        st1.setHight(178L);
        st1.setSex(1);
        st1.setName("韩梅梅");
        allList.add(st1);
        Student st11 = new Student();
        st11.setAge(20);
        st11.setHight(168L);
        st11.setSex(1);
        st11.setName("马冬梅");
        allList.add(st11);

        Student st2 = new Student();
        st2.setAge(21);
        st2.setHight(179L);
        st2.setSex(2);
        st2.setName("李磊");
        allList.add(st2);
        Student st22 = new Student();
        st22.setAge(21);
        st22.setHight(189L);
        st22.setSex(2);
        st22.setName("小李");
        allList.add(st22);

        // 以年龄 和 性别 分组，并选取最高身高的 学生
        Map<Long, Optional<Student>> allMapTask = allList.stream().collect(
                Collectors.groupingBy(Student::getIwantStudent, Collectors.maxBy((o1, o2) -> o1.getHight().compareTo(o2.getHight()))));

        System.out.println(allMapTask);
        //{21=Optional[Student{name='韩梅梅', age=20, hight=178, sex=1}], 23=Optional[Student{name='小李', age=21, hight=189, sex=2}]}

        // 遍历获取对象信息
        for (Map.Entry<Long,Optional<Student>> entry: allMapTask.entrySet()) {
            Student student = entry.getValue().get();
            System.out.println(student.toString());
        }
        //Student{name='韩梅梅', age=20, hight=178, sex=1}
        //Student{name='小李', age=21, hight=189, sex=2}
    }
}