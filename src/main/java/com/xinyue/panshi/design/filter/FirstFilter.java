package com.xinyue.panshi.design.filter;

import com.xinyue.panshi.common.util.PrintUtil;

/**
 * @author hxy
 * @time 2018/1/29
 * @desc
 */
public class FirstFilter implements Filter {
    @Override
    public void init() {

    }

    @Override
    public void doFilter(FilterChain filterChain) {
        PrintUtil.printString("第一次过滤");
        filterChain.doFilter();
    }

    @Override
    public void destroy() {

    }
}
