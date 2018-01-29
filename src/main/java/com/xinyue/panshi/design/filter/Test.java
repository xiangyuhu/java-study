package com.xinyue.panshi.design.filter;

/**
 * @author hxy
 * @time 2018/1/29
 * @desc
 */
public class Test {
    public static void main(String [] args){
        FilterChain filterChain = new FilterChain();
        filterChain.setTarget(new Servlet());
        filterChain.addFilter(new FirstFilter());
        filterChain.addFilter(new SecondFilter());
        filterChain.doFilter();
    }
}
