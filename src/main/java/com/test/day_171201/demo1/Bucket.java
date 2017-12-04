package com.test.day_171201.demo1;

import java.util.concurrent.atomic.AtomicInteger;

public class Bucket {
    private AtomicInteger count = new AtomicInteger(0);

    public AtomicInteger getCount() {
        return count;
    }

    public void setCount(AtomicInteger count) {
        this.count = count;
    }
}