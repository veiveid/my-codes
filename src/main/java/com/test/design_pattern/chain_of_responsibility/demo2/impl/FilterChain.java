package com.test.design_pattern.chain_of_responsibility.demo2.impl;

import com.test.design_pattern.chain_of_responsibility.demo2.Filter;
import com.test.design_pattern.chain_of_responsibility.demo2.Request;
import com.test.design_pattern.chain_of_responsibility.demo2.Response;

public class FilterChain implements Filter {
    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {

    }
}