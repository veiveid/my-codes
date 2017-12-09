package com.test.fan_xing.crud;

import java.io.Serializable;
import java.util.Collection;

public interface DepartmentDao<T> extends BaseDao<T>{
    //对department操作的各种方法
    public void saveDepartment(Department department);
    public void updateDepartment(Department department);
    public void deleteDepartmentById(Department department, String deleteMode);
    public Collection<Department> getAllDepartments();
    public Department getDepartmentById(Department department);
}