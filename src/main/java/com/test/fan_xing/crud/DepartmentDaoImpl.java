package com.test.fan_xing.crud;

import java.io.Serializable;
import java.util.Collection;

public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao<Department> {
    @Override
    public void saveDepartment(Department department) {
        this.saveEntry(department);
    }

    @Override
    public void updateDepartment(Department department) {
        this.updateEntry(department);
    }

    @Override
    public void deleteDepartmentById(Department department, String deleteMode) {
        this.deleteEntryById(department,department.getId());
    }

    @Override
    public Collection<Department> getAllDepartments() {
        return this.getAllEntries();
    }

    @Override
    public Department getDepartmentById(Serializable id) {
        return this.getEntryById(Department.class,id);
    }
}