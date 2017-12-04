package com.test.day_171129.demo3;

public enum Color implements Behaviour{
    RED("红",1),GREEN("绿",2),YELLOW("黄",3);
    private String name;
    private int index;

    private Color(String name, int index) {
        this.name = name;
        this.index = index;
    }

    @Override
    public void print() {
        System.out.println(this.index+":"+this.name);
    }

    @Override
    public String getInfo() {
        return this.name;
    }

}