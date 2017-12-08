package com.test.fan_xing.demo2;

import java.util.ArrayList;
import java.util.Collection;

class Person2{
    String name;
    int age;

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

interface Factory<T>{
    public T make();
}

public class SqlUtil2 {
    public static <T> Collection<T> select(Factory<T> factory,String statement){
        Collection<T> results = new ArrayList<T>();
         /* run sql query using jdbc */
        for ( int i=0; i<10; i++ ) { /* iterate over jdbc results */
            T item = factory.make();
            /* use reflection and set all of item’s fields from sql results */
            results.add( item );
        }
        return results;
    }

    public static void main(String[] args) {
        SqlUtil2.select(new Factory<Person2>() {

            @Override
            public Person2 make() {
                return new Person2();
            }
        },"select * from t");
    }
}