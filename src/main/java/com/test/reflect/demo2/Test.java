package com.test.reflect.demo2;

import java.lang.reflect.Method;

public class Test {

    /**
     * 获取当前类的父类中定义的私有方法
     * 直接调用getSuperclass()
     */
    public void testGetSuperClass() throws ClassNotFoundException {
        String className = "com.test.reflect.demo2.StudentInvoke";

        Class clazz = Class.forName(className);
        Class superClass = clazz.getSuperclass();
        System.out.println(superClass);
    }

    /**
     * @param className  某个类的全类名
     * @param methodName 类的一个方法的方法名，该方法也可能是私有方法
     * @param args  调用该方法需要传入的参数 ...可变参数的意思
     * @return 调用方法后的返回值
     */
    public Object invoke(String className,String methodName,Object...args) throws Exception {
        Object obj = null;
        obj = Class.forName(className).newInstance();
        return invoke(obj,methodName,args);
    }

    /**
     * @param obj  方法执行的那个对象
     * @param methodName 类的一个方法的方法名，该方法也可能是私有方法,还可能是该方法在父类中定义的私有方法
     * @param args  调用该方法需要传入的参数 ...可变参数的意思
     * @return 调用方法后的返回值
     */
    public Object invoke(Object obj, String methodName, Object ... args) throws Exception {
        //1、获取Method对象
        Class[] parameterTypes = new Class[args.length];
        for (int i=0;i<args.length;i++){
            parameterTypes[i] = args[i].getClass();
        }

        //2、执行Method方法
        Method method = getMethod(obj.getClass(),methodName,parameterTypes);

        //通过反射执行private方法
        method.setAccessible(true);

        //3、返回方法的返回值
        return method.invoke(obj,args);

    }

    /**
     * 获取clazz 的methodName 方法， 该方法可能是私有方法，还可能是父类中的私有方法
     */
    public Method getMethod(Class clazz, String methodName, Class...parameterTypes) throws Exception {
        //注意这个循环里的内容！！！
        for(;clazz != Object.class;clazz.getSuperclass()){
            return clazz.getDeclaredMethod(methodName,parameterTypes);
        }
        return null;
    }


    public void testInvoke2() throws Exception {
        Object obj = new StudentInvoke();
        invoke(obj.getClass(),"method2",10);
    }


    public static void main(String[] args) {
        Test t = new Test();
        try {
            //t.testGetSuperClass();
            t.testInvoke2();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}