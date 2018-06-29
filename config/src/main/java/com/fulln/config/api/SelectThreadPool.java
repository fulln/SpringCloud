package com.fulln.config.api;

import com.fulln.config.entity.reflectEntity;
import com.fulln.config.entity.refletMapEntity;
import com.fulln.config.util.ExecPoolUtil;
import com.fulln.config.util.reflectUtil;

import java.util.List;
import java.util.concurrent.Future;

import static com.fulln.config.util.reflectUtil.setReflectUtil;

/**
 * @AUthor: fulln
 * @Description: 将反射与多线程组合起来调用其接口(想到更好的实现方式)
 * 通过内层注解标明当前方法是要进行多线程查询的一个方法（即  不用写多余的方法的处理参数与方法名）；
 * 通过外层注解将所有标注了内层注解的方法封箱成要实现的实体，外层注解加上一个内层数量的属性
 *
 * @Date : Created in  16:23  2018/6/10.
 */

public interface SelectThreadPool {

    List<refletMapEntity> setRefList();

//    default void setAllTothisList(Class T){
//        reflectUtil rc  = setReflectUtil(T);
//        rc.init(setRefList());
//        List<reflectEntity> li =   rc.getReflectList();
//        ExecPoolUtil execPoolUtil =new ExecPoolUtil() {
//            @Override
//            public Object finishRun() {
//                li.forEach(list ->{
//                    if(list.getMethod().getReturnType().getName().lastIndexOf("void") !=-1){
//                        rc.getNoneByName(list);
//
//                    }else{
//                         rc.getResultByName(list);
//                    }
//                });
//                return null;
//            }
//        };
//        List<Future<Object>> futureList =  execPoolUtil.getReturnBack();
//        futureList.forEach(flist ->{
//
//
//
//        });
//
//
//    }

}
