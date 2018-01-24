package com.xinyue.panshi.thread.threadLocal;

/**
 * @author hxy
 * @time 2018/1/19
 * @desc
 */
public class School {
    public int getMaleCount(){
        return StudentCountHolder.studentCountHolder.get().getMaleCount();
    }
}
