package com.test.design_pattern.chain_of_responsibility.demo2.impl;

import com.test.design_pattern.chain_of_responsibility.demo2.Filter;
import com.test.design_pattern.chain_of_responsibility.demo2.Request;
import com.test.design_pattern.chain_of_responsibility.demo2.Response;

//定义FaceFilter
public class FaceFilter implements Filter {
    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        //将字符串中出现的":):"转换成"^V^";
        String tem = request.getRequestStr();
        tem = request.getRequestStr().replace(":):", "^V^")
        //后面添加的是便于我们观察代码执行步骤的字符串
        + "----FaceFilter()";
        chain.doFilter(request, response, chain);
        String tem2 = response.getResponseStr();
        tem2 += "---FaceFilter()";
    }
}