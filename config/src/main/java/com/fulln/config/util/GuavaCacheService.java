package com.fulln.config.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 直接实现接口 比aop方便？
 */
public interface GuavaCacheService {

    Cache<Object, Object> cache = CacheBuilder.newBuilder()
            .maximumSize(1000)
//多种清缓存方式只能选择一种进行                        .refreshAfterWrite(10, TimeUnit.MINUTES)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    /**
     * 覆盖key
     *
     * @param key
     * @return
     */
    default Object getCache(String key) {

        Object m = null;
        try {
            m = cache.get(key, (Callable<Object>) () -> doThingsTheHardWay(key));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return m;
    }


    /**
     * 异步刷新
     *
     * @return
     * @paramkey
     */
    default LoadingCache<String, Object> OnTimeCache() {
        ListeningExecutorService backgroundRefreshPools = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(20));
        return CacheBuilder.newBuilder()
                .maximumSize(100)
                .refreshAfterWrite(10, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Object>() {
                    @Override
                    public Object load(String key) throws Exception {
                        return doThingsTheHardWay(key);
                    }

                    @Override
                    public ListenableFuture<Object> reload(final String key, Object prevGraph) throws Exception {
                        if (neverNeedsRefresh(key, prevGraph)) {
                            return Futures.immediateFuture(prevGraph);
                        } else {
                            return backgroundRefreshPools.submit((Callable<Object>) () -> doThingsTheHardWay(key));
                        }
                    }
                });
    }




    /**
     * key值获取方式
     * 方法抽象出来进行重写
     *
     * @param key
     * @return
     */
    Object doThingsTheHardWay(String key);

    /**
     * 判断当前需不需要重载key
     * true不需要刷新
     */
    Boolean neverNeedsRefresh(String key, Object prevGraph);

}
