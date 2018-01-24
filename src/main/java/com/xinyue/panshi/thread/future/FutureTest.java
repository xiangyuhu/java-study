package com.xinyue.panshi.thread.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author hxy
 * @time 2018/1/23
 * @desc
 */
public class FutureTest {


    public static void main (String [] args[]) {
        /**
         * Callable<T> task 的使用配合线程池， 返回的结果就是Future
         */
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        Future<Integer> future = executorService.submit(()-> new RpcService().getResult());
    }

    static class RpcService {
       public Integer getResult() {
           return 1;
       }
    }
}
