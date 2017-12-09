package com.test.fan_xing.crud;

import java.util.Set;

public class Department {
    private Integer id;
    private Long did;//部门id
    private String dname;//部门名称
    private String description;//部门描述
    private Set<User> users;//部门里面所有的用户

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getDid() {
        return did;
    }
    public void setDid(Long did) {
        this.did = did;
    }
    public String getDname() {
        return dname;
    }
    public void setDname(String dname) {
        this.dname = dname;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Set<User> getUsers() {
        return users;
    }
    public void setUsers(Set<User> users) {
        this.users = users;
    }
}