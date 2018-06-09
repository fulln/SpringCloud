//package com.fulln.config;
//
//import com.fulln.config.entity.ThreadEntity;
//import com.fulln.config.util.ThreadPoolUtil;
//
//import java.lang.reflect.InvocationTargetException;
//
///**
// *
// * 项目名称：QueryDAO
// * 类名称：ThreadDemo
// * 类描述：一个多线程连接的sql查询demo
// * 创建人：fulln
// * 创建时间：2018年1月22日 上午11:17:06
// * @version
// *
// */
//public class ThreadDemo extends ThreadPoolUtil {
//
////    private WideCls<?> wide;
////
////    public WideCls<?> getWide() {
////        return wide;
////    }
////
////    public void setWide(WideCls<?> wide) {
////        this.wide = wide;
////    }
//
//
//    ConcurrentLinkedQueue<User> cqli = new ConcurrentLinkedQueue<User>();//线程安全的list集合
//
//    List<User> usLi = new ArrayList<User>();//装载list返回值集合
//
//    /**
//     * setCondition(设置传入的参数)
//     * TODO()
//     * @param name
//     * @param clazz
//     * @param map
//     * void
//     * 2018-01-25
//     */
//        public void setCondition(String name,Class<?> clazz,Map<String,Object> map) {
//            usLi = new ArrayList<User>();
//            ThreadEntity tey = new ThreadEntity();
//            tey.setMethodName(name);
//            tey.setClazz(clazz);
//            tey.setCondition(map);
//            getLi().add(tey);
//        }
//
//
//    @Override
//    public synchronized void meta(Object oc, Object[] objects) {
//        try {
//            clazz u = (clazz)getRefle().invoke(oc, objects);
//            cqli.add(u);
//        } catch (IllegalAccessException |IllegalArgumentException |InvocationTargetException e) {
//            e.printStackTrace();
//
//        }
//    }
//
//
//
//    @Override
//    public void found() {
//        usLi.addAll(cqli);
//    }
//
//    public List<User> getUsLi() {
//        return usLi;
//    }
//    public void setUsLi(List<User> usLi) {
//        this.usLi = usLi;
//    }
//
//}