package com.test.fan_xing.crud;

import java.io.Serializable;
import java.util.Collection;

//在这个类中，我们引入了泛型T，代表需要返回或者操作的java对象
public interface BaseDao<T> {
    Collection<T> getAllEntries();
    T getEntryById(Serializable id);
    void saveEntry(T t);
    void updateEntry(T t);
    void deleteEntryById(Serializable id);
}