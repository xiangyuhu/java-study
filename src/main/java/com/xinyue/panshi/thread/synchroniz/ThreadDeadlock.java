package com.xinyue.panshi.thread.synchroniz;

/**
 * @author hxy
 * @time 2018/1/23
 * @desc
 */
public class ThreadDeadlock {
    public static void main(String[] args) throws InterruptedException {
        String obj1 = "object1";
        String obj2 = "object2";
        Thread t1 = new Thread(new SyncThread(obj1, obj2), "t1");
        Thread t2 = new Thread(new SyncThread(obj2, obj1), "t2");

        t1.start();
        t2.start();
    }

}

class SyncThread implements Runnable {
    private Object obj1;
    private Object obj2;

    public SyncThread(Object o1, Object o2) {
        this.obj1 = o1;
        this.obj2 = o2;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        synchronized (obj1) {
            System.out.println(name + " acquired lock on " + obj1);
            try {
                // 确保让第二线程获取obj2锁
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " acquiring lock on " + obj2);
            synchronized (obj2) {
                System.out.println(name + " acquired lock on " + obj2);
            }
            System.out.println(name + " released lock on " + obj2);
        }
        System.out.println(name + " released lock on " + obj1);
        System.out.println(name + " finished execution.");
    }
}
