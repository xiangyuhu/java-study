package com.xinyue.panshi.thread.pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author hxy
 * @time 2018/1/23
 * @desc
 */
public class PoolTest {
    public static void  main (String [] args) {
        LinkedBlockingQueue<Runnable> workBlockingQueue = new LinkedBlockingQueue<Runnable>();
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("SensorDataQueue-pool-%d").build();
        int threadNumber = Runtime.getRuntime().availableProcessors() << 1;
        new ThreadPoolExecutor(1, 6,1000, TimeUnit.MINUTES, workBlockingQueue, namedThreadFactory);
    }

}
