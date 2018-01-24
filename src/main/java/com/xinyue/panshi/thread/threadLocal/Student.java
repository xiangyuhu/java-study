package com.xinyue.panshi.thread.threadLocal;

/**
 * @author hxy
 * @time 2018/1/19
 * @desc
 */
public class Student {
    private String name;
    private String sex;

    public Student(String name, String sex){
        this.name = name;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
