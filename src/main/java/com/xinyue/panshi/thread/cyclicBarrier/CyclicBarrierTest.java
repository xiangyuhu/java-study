package com.xinyue.panshi.thread.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hxy
 * @time 2018/1/19
 * @desc
 */
public class CyclicBarrierTest {

    static AtomicBoolean isSqlUpdated = new  AtomicBoolean(false);
    static AtomicInteger updateValue = new  AtomicInteger(0);

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(4);
        // 下面模拟APP服务器（负载2台）更新代码操作
        new MyThread(barrier,"APP服务器1").start();
        new MyThread(barrier,"APP服务器2").start();
        new MyThread(barrier,"APP服务器3").start();
        new MyThread(barrier,"APP服务器4").start();

    }

    static class MyThread extends Thread {

        private String serveName;
        private CyclicBarrier cyclicBarrier;

        public MyThread(CyclicBarrier cyclicBarrier, String serveName) {
            this.cyclicBarrier = cyclicBarrier;
            this.serveName = serveName;
        }

        @Override
        public void run() {
            System.out.println(serveName + "打包上传成功");
            System.out.println(serveName + "正在停止服务");
            System.out.println(serveName + "正在进行备份操作");
            try {
                System.out.println(serveName + "服务停止完毕");
                // 必须等待其他机器都停止了，才能执行新的脚本
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
           
            if (isSqlUpdated.compareAndSet(false, true)) {
                 System.out.println(serveName + "执行数据库脚本更新");
            }

            if (updateValue.compareAndSet(0, 1)) {
                System.out.println(serveName + "执行数据库脚本更新2");
            }

            System.out.println(serveName + "继续执行部署操作");
        }
    }
}
