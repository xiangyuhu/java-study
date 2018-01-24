package com.xinyue.panshi.thread.synchroniz;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author hxy
 * @time 2018/1/19
 * @desc
 */
public class LockTest {
    private String shareString = "wo";

    private Lock lock = new ReentrantLock();
    final static LockTest test = new LockTest();

    private void lockMethodTest(String name) {
        lock.lock();
        try {
            System.out.println(name + "得到了锁");
        } catch (Exception e) {

        } finally {
            System.out.println(name + "释放了锁");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        int threadNum = 10;
        for (int i = 0; i < threadNum; i++) {
            new MyThread("thread" + i).start();
        }
    }


    static class MyThread extends Thread {

        private String name;

        public MyThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            test.lockMethodTest(name);
        }
    }
}
