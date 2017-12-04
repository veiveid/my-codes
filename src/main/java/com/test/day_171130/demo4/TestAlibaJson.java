package com.test.day_171130.demo4;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Date;

public class TestAlibaJson {
    private String namePrefix;
    private Integer bookMount;
    @JSONField(name = "address")
    private String address;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;

    @JSONField(name = "name_prefix")
    public String getNamePrefix() {
        return namePrefix;
    }

    @JSONField(name = "namePrefix")
    public void setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    @JSONField(name = "book_mount")
    public Integer getBookMount() {
        return bookMount;
    }

    @JSONField(name = "bookMount")
    public void setBookMount(Integer bookMount) {
        this.bookMount = bookMount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        TestAlibaJson t = new TestAlibaJson();
        t.setBookMount(10);
        t.setNamePrefix("tom");
        t.setAddress(null);
        t.setBirthday(new Date());
        System.out.println(FastJsonUtil.toJSONString(t));
        //System.out.println(JSONObject.toJSONString(t, SerializerFeature.WriteDateUseDateFormat));
    }
}