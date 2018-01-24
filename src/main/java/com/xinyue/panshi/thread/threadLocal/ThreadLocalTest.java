package com.xinyue.panshi.thread.threadLocal;

import com.xinyue.panshi.common.util.PrintUtil;

import java.io.IOException;

/**
 * @author hxy
 * @time 2018/1/19
 * @desc
 */
public class ThreadLocalTest {
    public static void main(String [] args ) throws IOException {
        final Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                Student student = new Student("小强", "男");
                StudentCountHolder.studentCountHolder = new ThreadLocal<>();
                StudentCount studentCount = new StudentCount();
                studentCount.addMale();
                StudentCountHolder.studentCountHolder.set(studentCount);
                PrintUtil.printString("threadA, 设置男生总数是:" + StudentCountHolder.studentCountHolder.get().getMaleCount()) ;

                try {
                    Thread.sleep(2000);
                    int maleCount = StudentCountHolder.studentCountHolder.get().getMaleCount();
                    int femaleCount = StudentCountHolder.studentCountHolder.get().getFemaleCount();
                    PrintUtil.printString("threadA, 获取男生总数是:" + maleCount) ;
                    PrintUtil.printString("threadA, 获取女生总数是:" + femaleCount) ;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                StudentCount studentCount = new StudentCount();
                Student student = new Student("小芳", "女");
                studentCount.addFemale();
                Student student2 = new Student("小军", "男");
                studentCount.addMale();
                Student student3 = new Student("小明", "男");
                studentCount.addMale();
                Student student4 = new Student("小月", "女");
                studentCount.addFemale();
                StudentCountHolder.studentCountHolder = new ThreadLocal<>();
                StudentCountHolder.studentCountHolder.set(studentCount);
                PrintUtil.printString("threadB, 设置男生总数是:" + StudentCountHolder.studentCountHolder.get().getMaleCount()) ;
                PrintUtil.printString("threadB, 设置女生总数是:" + StudentCountHolder.studentCountHolder.get().getFemaleCount()) ;
            }
        });
        threadA.start();
        threadB.start();
    }
}
