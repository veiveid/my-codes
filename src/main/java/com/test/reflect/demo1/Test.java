package com.test.reflect.demo1;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {

    /**
     * 反射机制获取类有三种方法
     */
    public void testGetClass(){
        Class clazz = null;

        //1 直接通过类名.Class的方式得到
        clazz = Person.class;
        System.out.println("通过类名：" + clazz);

        //2 通过对象的getClass()方法获取,这个使用的少（一般是传的是Object，不知道是什么类型的时候才用）
        Object obj = new Person();
        clazz = obj.getClass();
        System.out.println("通过getClass()" + clazz);

        //3 通过全类名获取，用的比较多，但可能抛出ClassNotFoundException异常
        try {
            clazz = Class.forName("com.test.reflect.demo1.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("通过全类名获取：" + clazz);

    }

    /**
     * Class类的newInstance()方法，创建类的一个对象。
     */
    public void testNewInstance(){
        //使用Class类的newInstance()方法创建类的一个对象
        //实际调用的类的那个 无参数的构造器（这就是为什么写的类的时候，要写一个无参数的构造器，就是给反射用的）
        //一般的，一个类若声明了带参数的构造器，也要声明一个无参数的构造器
        try {
            Class clazz = Class.forName("com.test.reflect.demo1.Person");
            Object obj = clazz.newInstance();
            System.out.println(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ClassLoader类装载器
     */
    public void testClassLoader1() throws ClassNotFoundException {
        //1、获取一个系统的类加载器
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println("系统的类加载器-->" + classLoader);

        //2、获取系统类加载器的父类加载器(扩展类加载器（extensions classLoader）)
        classLoader = classLoader.getParent();
        System.out.println("扩展类加载器-->" + classLoader);

        //3、获取扩展类加载器的父类加载器
        //输出为Null,无法被Java程序直接引用
        classLoader = classLoader.getParent();
        System.out.println("启动类加载器-->" + classLoader);

        //4、测试当前类由哪个类加载器进行加载 ,结果就是系统的类加载器
        classLoader = Class.forName("com.test.reflect.demo1.Person").getClassLoader();
        System.out.println("当前类由哪个类加载器进行加载-->"+classLoader);

        //5、测试JDK提供的Object类由哪个类加载器负责加载的
        classLoader = Class.forName("java.lang.Object").getClassLoader();
        System.out.println("JDK提供的Object类由哪个类加载器加载-->" + classLoader);
    }

    public void testGetResourceAsStream() {
        //这么写的话，文件需要放到src目录下
        //InputStream in = new FileInputStream("test.properties");

        //5、关于类加载器的一个主要方法
        //调用getResourceAsStream 获取类路径下的文件对应的输入流
        InputStream in = this.getClass().getClassLoader().
                getResourceAsStream("/test.properties");
        System.out.println("in: " + in);
    }


    public void testMethod() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class clazz = Class.forName("com.test.reflect.demo1.Person");

        //1、得到clazz 对应的类中有哪些方法,不能获取private方法
        Method[] methods = clazz.getMethods();
        System.out.print("        getMethods: ");
        for (Method m : methods){
            System.out.println(m.getName()+ ",");
        }

        //2、获取所有的方法(且只获取当着类声明的方法，包括private方法）
        Method[] methods1 = clazz.getDeclaredMethods();
        System.out.print("\ngetDeclaredMethods: ");
        for (Method method : methods1){
            System.out.println(method.getName() + ",");
        }

        //3、获取指定的方法
        Method method = clazz.getDeclaredMethod("setName",String.class);//第一个参数是方法名，后面的是方法里的参数
        System.out.println("\nmethod : " + method);

        Method method2 = clazz.getDeclaredMethod("setName",String.class,int.class);//第一个参数是方法名，后面的是方法里的参数
        System.out.println("method2: " + method2);

        //4、执行方法！
        Object obj = clazz.newInstance();
        method2.invoke(obj,"zhangsan",21);

    }


    public static void main(String[] args) {
        Test test = new Test();
        try {
            //test.testGetClass();
            //test.testNewInstance();
            //test.testClassLoader1();
            //test.testGetResourceAsStream();
            test.testMethod();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}