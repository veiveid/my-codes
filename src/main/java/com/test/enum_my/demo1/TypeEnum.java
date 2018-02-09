package com.test.enum_my.demo1;

public enum TypeEnum {

    VIDEO(1, "视频"), AUDIO(2, "音频"), TEXT(4, "文本"), IMAGE(3, "图像");

    int value;
    String name;

    TypeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static TypeEnum getByValue(int value) {
        for(TypeEnum typeEnum : TypeEnum.values()) {
            /*if(typeEnum.value == value) {
                return typeEnum;
            }*/
            System.out.println(typeEnum.value);
        }
        return null;
        //throw new IllegalArgumentException("No element matches " + value);
    }

    public static void main(String[] args) {
        TypeEnum.getByValue(1);
    }
}