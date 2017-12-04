package com.test.day_171130.demo1;

import java.util.concurrent.TimeUnit;

public class TimeUnitTest {

    public static void main(String[] args) {
        //1小时转换分钟数为60分钟
        System.out.println(TimeUnit.HOURS.toMinutes(1));//参数1为一个小时
        //1小时转换分钟数为3600秒
        System.out.println(TimeUnit.HOURS.toSeconds(1));//参数1为一个小时
        //1小时转换分钟数为3600000秒
        System.out.println(TimeUnit.HOURS.toMillis(1));
        System.out.println(TimeUnit.DAYS.toMillis(1));

        //3600秒转换分钟数为60分钟
        System.out.println(TimeUnit.SECONDS.toMinutes(3600));//3600为3600秒
        //3600秒转换小时数为1小时
        System.out.println(TimeUnit.SECONDS.toHours(3600));//3600为3600秒
    }
}