package com.test.fan_xing.crud;

import org.springframework.orm.hibernate3.HibernateTemplate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

//子类里面的T是给父类里面T赋值，子类里面的T是实参，父类里面的t是形参
public class BaseDaoImpl<T> implements BaseDao<T> {

    public HibernateTemplate hibernateTemplate;
    private Class classt;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    /*
         * 在父类中，要执行一段代码，执行的时间是在子类创建对象的时候，那么有两种解决方案
         * 1、使用static代码块
         * 2、利用父类的构造函数
         *
         * 分析：如果需要使用到this，那么就要使用父类的构造函数，如果不需要使用daothis，可以使用static代码块
         * 因为下面需要使用this，获取ParameterizedType，所以使用父类的构造函数
         * 如何获取泛型里面的class
         */
    public BaseDaoImpl(){//使用父类的构造函数来实现对获取泛型的Class
        //ParameterizedType,就是泛型，这里的this不是BaseDaoImpl，而是BaseDaoImpl的子类的this，
        //也就是一个类会继承BaseDaoImpl，然后通过它来获取其父类BaseDaoImpl的泛型T
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获取到泛型的Class
        this.classt = (Class) type.getActualTypeArguments()[0];
    }

    /*
　　有人会问，为什么要获取泛型的Class，对应hibernate操作数据库来说，获取一个泛型的实例，需要通过泛型的Class来获取
　　说白了，没有泛型的Class,就无法获取泛型的实例对象，也就没办法返回给service层实例对象
   */

    @Override
    public Collection<T> getAllEntries() {
        return this.hibernateTemplate.find("from " + this.classt.getName());
    }

    @Override
    public T getEntryById(T t,Serializable id) {
        return (T)this.hibernateTemplate.get(t.getClass(),id);
    }

    @Override
    public void saveEntry(T t) {
        this.hibernateTemplate.save(t);
    }

    @Override
    public void updateEntry(T t) {
        this.hibernateTemplate.update(t);

    }

    @Override
    public void deleteEntryById(T t,Serializable id) {
        T t2 = this.getEntryById(t,id);
        this.hibernateTemplate.delete(t2);
    }

    public static void main(String[] args) {
        System.out.println(1212);
    }
}