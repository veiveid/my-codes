package com.test.fan_xing.demo2;

import java.util.ArrayList;
import java.util.Collection;

class Person{
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

public class SqlUtil {

    public <T> Collection<T> select(Class<T> c, String sqlStatement) throws IllegalAccessException, InstantiationException {
        Collection<T> results = new ArrayList<T>();
         /* run sql query using jdbc */

        for ( int i=0;i<2;i++/* iterate over jdbc results */){
            T item = c.newInstance();
            /* use reflection and set all of item’s fields from sql results */
            results.add(item);
        }
        return results;
    }
}