package com.test.day_171123.countDownLatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ApplicationStartupUtil {
    private static List<BaseHealthChecker> _services;
    private static CountDownLatch _latch;

    private ApplicationStartupUtil() {
    }

    private final static ApplicationStartupUtil INSTANCE = new ApplicationStartupUtil();

    public static ApplicationStartupUtil getInstance(){
        return INSTANCE;
    }

    public static boolean checkExternalServices() throws Exception{
        //Initialize the latch with number of service checkers
        _latch = new CountDownLatch(3);

        //All add checker in lists
        _services = new ArrayList<BaseHealthChecker>();
        _services.add(new NetworkHealthChecker(_latch));
        _services.add(new DatabaseHealthChecker(_latch));
        _services.add(new CacheHealthChecker(_latch));

        //Start service checkers using executor framework
        Executor executor = Executors.newFixedThreadPool(_services.size());

        for (BaseHealthChecker s : _services){
            executor.execute(s);
        }
        //Now wait till all services are checked
        _latch.wait();

        for (BaseHealthChecker s : _services){
            if (!s.isServiceUp()) return false;
        }
        return true;
    }
}