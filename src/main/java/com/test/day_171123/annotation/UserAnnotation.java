package com.test.day_171123.annotation;

import java.util.HashMap;
import java.util.Map;


public class UserAnnotation {

    @TestA(name="pulic method",id=3,gid=Long.class)
    public void a(){
        Map<String,String> m = new HashMap<String,String>(0);
    }
}