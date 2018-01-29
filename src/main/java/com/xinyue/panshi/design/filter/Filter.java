package com.xinyue.panshi.design.filter;

import java.io.IOException;

/**
 * @author hxy
 * @time 2018/1/29
 * @desc 典型的servlet规范过滤器配置
 */
public interface Filter {
    void init();
    void doFilter(FilterChain filterChain);
    void destroy();
}
