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
    public void deleteDepartmentById(Serializable id, String deleteMode) {
        this.deleteEntryById(id);
    }

    @Override
    public Collection<Department> getAllDepartments() {
        return this.getAllEntries();
    }

    @Override
    public Department getDepartmentById(Serializable id) {
        return this.getEntryById(id);
    }
}