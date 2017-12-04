package com.test.day_171129.demo2;

public enum Color {
    RED("红色",1,"颜色是红色的","colume1"),
    GREEN("绿色",2,"颜色是绿色的","colume2"),
    YELLOW("黄色",3,"颜色是黄色的","colume3");
    private String name;
    private int index;
    private String desc;
    private String colume;

    private Color(String name, int index,String desc,String colume){
        this.name = name;
        this.index = index;
        this.desc = desc;
        this.colume = colume;
    }

    public static String getName(int index){
        for (Color c : Color.values()){
            if(index == c.getIndex()){
                return c.getName();
            }
        }
        return null;
    }

    public static String getDesc(int index){
        for (Color c : Color.values()){
            if(index == c.getIndex()){
                return c.getDesc();
            }
        }
        return null;
    }

    public static String getColume(int index){
        for (Color c : Color.values()){
            if(index == c.getIndex())
                return c.getColume();
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getColume() {
        return colume;
    }

    public void setColume(String colume) {
        this.colume = colume;
    }
}