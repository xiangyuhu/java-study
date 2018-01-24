package com.xinyue.panshi.thread.threadLocal;

/**
 * @author hxy
 * @time 2018/1/19
 * @desc
 */
public class StudentCount {
    private int maleCount;
    private int femaleCount;

    public void addMale(){
        this.maleCount ++;
    }

    public void addFemale(){
        this.femaleCount ++;
    }

    public int getMaleCount() {
        return maleCount;
    }

    public void setMaleCount(int maleCount) {
        this.maleCount = maleCount;
    }

    public int getFemaleCount() {
        return femaleCount;
    }

    public void setFemaleCount(int femaleCount) {
        this.femaleCount = femaleCount;
    }
}
