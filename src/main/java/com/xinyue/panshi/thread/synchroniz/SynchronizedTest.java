package com.xinyue.panshi.thread.synchroniz;

import com.xinyue.panshi.common.util.PrintUtil;

import java.io.IOException;

/**
 * @author hxy
 * @time 2018/1/19
 * @desc
 */
public class SynchronizedTest {
    private  String shareString = "我";

    static SynchronizedTest synchronizedTest = new SynchronizedTest();

    public static void main(String [] args ) throws IOException {
        int threadNum = 10;
        for (int i = 0; i < threadNum; i++) {
            new MyThread("thread" + i, synchronizedTest).start();
        }
    }
    public synchronized void synchronizedStaticMethodTest(String name) {
        PrintUtil.printString(name + "在执行synchronizedStaticMethodTest 第一次");
        PrintUtil.printString(name + "在执行synchronizedStaticMethodTest 第二次");
    }

    public synchronized void synchronizedMethodTest(String name) {
        PrintUtil.printString(name + "在执行SynchronizedMethodTdest 第一次");
        PrintUtil.printString(name + "在执行SynchronizedMethodTest 第二次");
    }

    public void synchronizedMethodBlockTest(String name){
        synchronized (shareString){
            PrintUtil.printString(name + "在执行synchronizedMethodBlockTest 第一次");
            PrintUtil.printString(name + "在执行synchronizedMethodBlockTest 第二次");
        }
    }

    static class MyThread extends Thread {

        private String name;
        /**
         * 1。共享的实例synchronizedTest SynchronizedMethodTest方法同一事件只允许一个线程实例执行
         * 因此执行结果可以预见俩个打印方法一定是执行完，其他线程才能进入
         * 2。和修饰方法类似
         */
        SynchronizedTest synchronizedTest;

        public MyThread(String name, SynchronizedTest synchronizedTest) {
            this.name = name;
            this.synchronizedTest = synchronizedTest;
        }
        @Override
        public  void run (){
            try {
                synchronizedTest.synchronizedStaticMethodTest(name);
                Thread.sleep(3000);
                synchronizedTest.synchronizedMethodTest(name);
                // 便于看下面结果测试
                Thread.sleep(3000);
                synchronizedTest.synchronizedMethodBlockTest(name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
