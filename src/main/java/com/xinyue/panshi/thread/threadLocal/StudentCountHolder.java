package com.xinyue.panshi.thread.threadLocal;

/**
 * @author hxy
 * @time 2018/1/19
 * @desc
 */
public class StudentCountHolder {
    public static ThreadLocal<StudentCount> studentCountHolder = new ThreadLocal<>();;
}
