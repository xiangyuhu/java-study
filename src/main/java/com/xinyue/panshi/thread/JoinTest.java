package com.xinyue.panshi.thread;

import com.xinyue.panshi.common.util.PrintUtil;

/**
 * @author hxy
 * @time 2018/1/18
 * @desc
 */
public class JoinTest {
    public static void  main(String args[]) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                PrintUtil.printString("线程A执行完毕");
            }
        });
        threadA.start();
        threadA.join();
        PrintUtil.printString("主线程执行完毕");
    }
}
