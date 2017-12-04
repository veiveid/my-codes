package com.test.design_pattern.chain_of_responsibility.demo2.impl;

import com.test.design_pattern.chain_of_responsibility.demo2.Filter;
import com.test.design_pattern.chain_of_responsibility.demo2.Request;
import com.test.design_pattern.chain_of_responsibility.demo2.Response;

//定义的过滤敏感字眼的过滤规则
public class SensitiveFilter implements Filter {
    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        String tem = request.getRequestStr();
        tem = request.getRequestStr().replace("被就业","就业").replace("敏感","")+
                //后面添加的是便于我们观察代码执行步骤的字符串
                " ---sensitiveFilter()";
        chain.doFilter(request,response,chain);
        String tem2 = response.getResponseStr();
        tem2 += "---sensitiveFilter()";
    }
}