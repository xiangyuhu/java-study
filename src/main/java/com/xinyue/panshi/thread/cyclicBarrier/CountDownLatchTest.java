package com.xinyue.panshi.thread.cyclicBarrier;

import java.util.concurrent.CountDownLatch;

/**
 * @author hxy
 * @time 2018/1/19
 * @desc
 */
public class CountDownLatchTest {


    public static void main(String[] args) {
        System.out.println("等待小明，小强提交代码...");
        final CountDownLatch latch = new CountDownLatch(2);
        // 一个项目2个人一起协作，完成之后，要提交到SVN，最后打包部署到测试环境
        new MyThread(latch, "小明").start();
        new MyThread(latch, "小强").start();
        try {
            latch.await();
            System.out.println("小明，小强提交代码完毕");
            System.out.println("可以用jenkins打包部署了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class MyThread extends Thread{
        final CountDownLatch latch;
        private String name;
        public MyThread (CountDownLatch latch, String name){
            this.latch = latch;
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name + "提交代码完毕");
            latch.countDown();
        }
    }

}
