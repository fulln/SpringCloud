package com.fulln.config.util;

import java.util.concurrent.*;

/**
 * @Author: fulln
 * @Description:线程池util类
 * @Date: Created in 2018/6/9 0009
 */
public abstract class ExecPoolUtil {

    private void getStart() {

        ExecutorService executorService = Executors.newFixedThreadPool(11);//开启一个定长线程池

        final LinkedBlockingQueue<Future<Integer>> blockingQueue = new LinkedBlockingQueue<>(10);//开启一个阻塞队列

        final CompletionService<Integer> completionService =  new ExecutorCompletionService<>(executorService,blockingQueue);//

        completionService.submit(() -> {

            runWork();
            return null;
        });

    }

    abstract void runWork();


}
