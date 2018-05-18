package com.fulln.config.util;

import com.fulln.config.entity.ThreadEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: fulln
 * @Description:缓存线程池
 * @Date: Created in 2018/5/18 0018
 */
public class rmThreadPoolUtil {

    private static Logger log = LoggerFactory.getLogger(rmThreadPoolUtil.class);

    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);//设置最大并发为5条

    private Method refle;

    ConcurrentLinkedQueue<String> cqli = new ConcurrentLinkedQueue<String>();//线程安全的list集合
    /**
     * 1.先获取当前的map大小， 作为循环的size (抽象为把所有的map中的值 存入list<threadpoolutil>中)
     * 2.将map中的值通过反射的形式注入到指定的程序中（抽象为遍历list 进行execute）
     * 3，开启线程，获取结果
     * 4，将所有的结果返回  给到list中
     */
    private void getStart(List<ThreadEntity> threadList) {

        final CountDownLatch countDownLatch = new CountDownLatch(threadList.size());//线程计数器

        final List<ThreadEntity> Threadli = new ArrayList<ThreadEntity>();//查询的参数list

        Threadli.addAll(threadList);

        Class ot =null;

        for (ThreadEntity threadEntity:Threadli) {//开始开辟线程
            //这里是没有采用bean的
            final Class tgg = threadEntity.getClazz();
            Map<String, Object> map = new HashMap<>();

            List<Object> resultli = new ArrayList<>();
            Method[] me = tgg.getDeclaredMethods();
            String name = threadEntity.getMethodName();
            map = threadEntity.getCondition();

            for (Method method : me) {
                if (name != null && method.getName().equals(name)) {
                    if (!method.isAccessible()) { //判断是不是公共方法
                        method.setAccessible(true);
                    }
                    ot= method.getReturnType();
                    Parameter[] p = method.getParameters();
                    for (int j = 0; j < (p == null ? 0 : p.length); j++) { //  将参数全部取出来
                        for (Map.Entry<String, Object> e : map.entrySet()) {
                            if (e.getKey().equals(p[j].getName())) {//1.8提供的新方法可以取出参数名
                                resultli.add(e.getValue());
                            }
                        }
                    }
                    setRefle(method);
                }
            }

            Class finalOt = ot;

            fixedThreadPool.execute(()->{
                meta(tgg, resultli.toArray());
                countDownLatch.countDown();
            });



        }


    }

    public Method getRefle() {
        return refle;
    }

    public void setRefle(Method refle) {
        this.refle = refle;
    }



    public  synchronized void meta(Class oc, Object[] objects){};


}
