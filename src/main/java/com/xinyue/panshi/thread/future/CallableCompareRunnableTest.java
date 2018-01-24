package com.xinyue.panshi.thread.future;

import com.xinyue.panshi.common.util.PrintUtil;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author hxy
 * @time 2018/1/18
 * @desc
 */
public class CallableCompareRunnableTest {


    public static void  main(String args[]) throws ExecutionException, InterruptedException {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "《callable 执行完毕》";
            }
        };

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                PrintUtil.printString("runnable 执行没有返回结果且不抛异常");
            }
        };
        /**
         *  可以考虑FutureTask, Future的关系 FutureTask implements RunnableFuture<V>  extends Runnable, Future<V>
         *  extends Runnable, Future 因此FutureTask 既有future.get()来获取callable返回值，也可以作为Runnable用
         *  而future一帮配合 callable 在 ExecutorService 中执行 <T> Future<T> submit(Callable<T> task)。;
         */
        FutureTask<String> task = new FutureTask<>(callable);
        // runnable直接执行run是不会是新的线程
        new Thread(runnable).start();
        new Thread(task).start();
        // get()方法会阻塞线程，下面执行的代码必须等该线程执行完成
        String result = task.get();
        PrintUtil.printString("FutureTask执行完毕返回结果：" + result);
        PrintUtil.printString("主线程执行完毕");
    }
}
