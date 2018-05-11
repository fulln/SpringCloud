package com.fulln.apigateway.Filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: fulln
 * @Description:
 * @Date: Created in 2018/5/10 0010
 */
public class GlobalFilter extends ZuulFilter {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    //根据这个方法指定过滤器的有效范围
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {


        return null;
    }
}
