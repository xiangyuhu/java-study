package com.xinyue.panshi.design.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hxy
 * @time 2018/1/29
 * @desc
 */
public class FilterChain {
    private List<Filter> filters = new ArrayList<>();
    private int currentPosition = 0;

    private Servlet servlet;
    void doFilter(){
        if (currentPosition < this.filters.size()) {
            currentPosition++;
            filters.get(currentPosition - 1).doFilter(this);
        }
        else{
            servlet.execute();
        }
    }

    public void addFilter(Filter filter){
        filters.add(filter);
    }

    public void setTarget(Servlet servlet){
        this.servlet = servlet;
    }

}
