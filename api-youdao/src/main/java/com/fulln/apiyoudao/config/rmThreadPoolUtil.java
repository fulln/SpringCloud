package com.fulln.apiyoudao.config;

import com.fulln.apiyoudao.Exception.YDtranslateException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: fulln
 * @Description:翻译缓存线程池
 * @Date: Created in 2018/5/18 0018
 */
@Slf4j
public class rmThreadPoolUtil {
    //设置最大并发为5条
    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
    // 线程安全的map
    private ConcurrentHashMap syncMap = new ConcurrentHashMap<String, String>();


//    @Resource
//    private ITranslateService translateService;

    /**
     * 1.先获取当前的map大小， 作为循环的size (抽象为把所有的map中的值 存入list<threadpoolutil>中)
     * 2.将map中的值通过反射的形式注入到指定的程序中（抽象为遍历list 进行invoke）
     * 3，开启线程，获取结果 查询出的结果方法同步map中
     * 4，返回map,关闭线程池
     * <p>
     */
    @SuppressWarnings("unchecked")
    public Map getStart(Map map) throws YDtranslateException {

        final CountDownLatch countDownLatch = new CountDownLatch(map.size());//线程计数器
        try {
            //设置最大并发为5条
            map.forEach((k, v) -> {
                fixedThreadPool.execute(() -> {
                    String values = ydTransUtil.getTrans(v.toString());
                    syncMap.put(k, values);
                    countDownLatch.countDown();
                });
            });
            countDownLatch.await();
            if (syncMap.size() > 0) {
                return syncMap;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error("线程池开启多线程翻译出现问题");
        } finally {
            fixedThreadPool.shutdown();
        }
        throw new YDtranslateException("翻译获得的结果为空");
    }
}
