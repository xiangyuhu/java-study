package com.xinyue.panshi.thread.cyclicBarrier;

import java.util.concurrent.Semaphore;

/**
 * @author hxy
 * @time 2018/1/19
 * @desc
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        int computer = 10;
        Semaphore semaphore = new Semaphore(10);
        // 20个小学生在排队上网
        for(int i = 0; i < 20; i++){
            new MyThread(i, semaphore).start();
        }

    }

    static class MyThread extends Thread{

        private int num;
        private Semaphore semaphore;

        public MyThread(int num,Semaphore semaphore){
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                String name = "小学生" + this.num;
                semaphore.acquire();
                System.out.println(name + "抢到了一台电脑在玩LOL");
                System.out.println("正在模拟" + name + "上网");
                System.out.println(name + "正在上网，很嗨");
                Thread.sleep(2000);
                System.out.println("网管监控到小学生" + this.num + "已经玩了1小时，时间到了");
                System.out.println("小学生" + this.num + "上午时间到了2块钱没了小学生下机了");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
