package com.xinyue.panshi.thread.future;

import com.google.common.collect.Interner;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


/**
 * @author hxy
 * @time 2018/1/23
 * @desc
 */
public class CompletableFutureTest {
    public static void main (String [] args[]) {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(()-> new RpcService().getResult(1));
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(()-> new RpcService().getResult(2));
        /**
         * 这个原生的比较麻烦谷歌guava提供了更好的方法
         */
        CompletableFuture<List<Integer>> result = CompletableFuture.allOf(future1, future2).thenApply((Void) -> {
            List <Integer> list = new ArrayList<>();
            try {
                list = Lists.newArrayList(future1.get(), future2.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                return  list;
            }
        });

        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(new ThreadPoolExecutor(
                2, 8, 0, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new ThreadFactoryBuilder().setDaemon(true).setNameFormat("xinyue-%d").build()));
        List<ListenableFuture<Integer>> result2 = new ArrayList<>(2);
        for(int i = 0; i < 2; i++) {
            final Integer input = i;
            result2.add(executorService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return input;
                }
            }));
        }
        Futures.allAsList(result2);

    }

    static class RpcService {
        public Integer getResult(Integer i) {
            return i;
        }
    }
}
