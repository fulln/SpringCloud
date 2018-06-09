package com.fulln.config.util;

import com.fulln.config.entity.ApplicationContextProvider;
import com.fulln.config.entity.ThreadEntity;
import com.fulln.config.entity.reflectEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/* 类名称：ThreadPoolUtil
 * 类描述：线程池查询
 * 创建人：fulln
 * 创建时间：2018年1月25日 下午7:15:22
 * @version
 *
 */
public class ThreadPoolUtil {

    //日志文件
    private static final Logger log = LoggerFactory.getLogger(ThreadPoolUtil.class);

    //开启基本的线程池 可以使用cachethreadpool
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 8, 3000, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(2000)) {
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
            printException(r, t);
        }
    };

    private  List<reflectEntity> li = new ArrayList<>();//查询的参数list

    private Method refle;

    //启动线程，根据数量决定要启动的线程数,么有开启的会进入线程池等待/最大5条
    public void getStart(){

        final CountDownLatch countDownLatch = new CountDownLatch( li.size() );//线程计数器

        final List<reflectEntity> Threadli = new ArrayList<>();//查询的参数list

        Threadli.addAll(li);

        for (int i = 0; i < Threadli.size(); i++) {//开始开辟线程
            //这里的部分 需要解耦合 当前是从springBean里面获取的
            final Object oc = ApplicationContextProvider.getBean(Threadli.get(i).getClazz());
            Map<String, Object> map;
            List<Object> resultli = new ArrayList<>();

            Class<? extends Object> tgg = oc.getClass();
            Method[] me = tgg.getDeclaredMethods();
            String name = Threadli.get(i).getMethodName();

            map = Threadli.get(i).getCondition();

            for (Method method : me) {
                if (name != null && method.getName().equals(name)) {
                    if (!method.isAccessible()) { //判断是不是公共方法
                        method.setAccessible(true);
                    }
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


            executor.execute(()->{
                meta(oc, resultli.toArray(),refle.getClass());
                countDownLatch.countDown();
            });
        }

        try {
            countDownLatch.await();
            found();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    synchronized void meta(Object oc, Object[] objects,Class clazz) {
    }

    void found() {
    }

    private static void printException(Runnable r, Throwable t) {
        if (t == null && r instanceof Future<?>) {
            try {
                Future<?> future = (Future<?>) r;
                if (future.isDone())
                    future.get();
            } catch (CancellationException ce) {
                t = ce;
            } catch (ExecutionException ee) {
                t = ee.getCause();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt(); // ignore/reset
            }
        }
        if (t != null)
            log.error(t.getMessage(), t);
    }

    public Method getRefle() {
        return refle;
    }

    public void setRefle(Method refle) {
        this.refle = refle;
    }

    public List<reflectEntity> getLi() {
        return li;
    }
}  