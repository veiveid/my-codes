package com.test.design_pattern.chain_of_responsibility.demo2;

import com.test.design_pattern.chain_of_responsibility.demo2.impl.FilterChain;

public interface Filter {
    void doFilter(Request request,Response response,FilterChain chain);
}