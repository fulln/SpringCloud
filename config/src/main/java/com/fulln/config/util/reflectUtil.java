package com.fulln.config.util;

import com.fulln.config.api.getAbstractClass;
import com.fulln.config.entity.ApplicationContextProvider;
import com.fulln.config.entity.reflectEntity;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 1.如果要是写多线程查询的话肯定是在逻辑层进行操作.
 * 2.通过autowired注解将所有需要执行的方法拿过来
 *
 *
 * @Author: fulln
 * @Description:反射util类
 * @Date: Created in 2018/6/8 0008
 */
@Slf4j
public class reflectUtil implements getAbstractClass {

    private List<reflectEntity> reflectList;

    private Class prepareClass;

    /**
     * 构造器直接初始化list
     * @param t
     */
    reflectUtil(Class t) {
        this.reflectList = new ArrayList<>();
        setPrepareClass(t);
    }

    /**
     * 将传入的类封装成一个list实体(查找类)
     * <p>
     * 1.获取当前class
     * 2.获取当前的methon
     * 3.获取当前参数
     * Map<String,list>  将方法名称和当前的参数组成map的形式传递
     */
    private void getThreadEntity(Class t, Map<String,List<Object>> map) {

        if(map.size() == 0){
            log.error("map中的值为空");
            throw new NullPointerException("map不能为空");
        }

        List<Field> fieldList = Arrays.asList(t.getClass().getDeclaredFields());
        fieldList.forEach(field -> {
                    String name = field.getName();
                    String files = Modifier.toString(field.getModifiers());
                    if (files.lastIndexOf("private") != -1) {
                        List<Annotation> annotation = Arrays.asList(field.getDeclaredAnnotations());
                        annotation.forEach(ano -> {
                            String val2 = ano.annotationType().getName();
                            if (val2.lastIndexOf("Autowired") != -1 || val2.lastIndexOf("Resource") != -1) {

                                final Object oc = getThisClazz(name);

                                List<Method> lis = Arrays.asList(oc.getClass().getDeclaredMethods());
                                lis.forEach(li -> {
                                    map.forEach((k,v) ->{
                                        if (li.getName().equals(k)) {
                                            //传入方法名称和参数
                                            reflectEntity reflect = new reflectEntity();
                                            reflect.setClazz(getThisClazz(name));
                                            reflect.setMethodName(k);
                                            reflect.setCondition(v);
                                            reflectList.add(reflect);

                                        }
                                    });

                                });
                            }

                        });
                    }
                }
        );

    }

    /**
     * 在service中调用
     *
     * @param map
     */
    public void init(Map<String,List<Object>> map) {
        getThreadEntity(getPrepareClass(),map);
        loopMethod();
    }

    /**
     * 遍历循环 转成对象  根据不同的方法进行不同的实例
     */
    @SuppressWarnings("unchecked")
    private void loopMethod() {

        reflectList.forEach(refli -> {

            final Object oc = refli.getClazz();

            Class tgg = oc.getClass();

            Method[] refmethod = tgg.getDeclaredMethods();

            String name = refli.getMethodName();


            for (Method method :
                    refmethod) {

                if (name != null && method.getName().equals(name)) {
                    if (!method.isAccessible()) { //判断是不是公共方法
                        method.setAccessible(true);
                    }
                    refli.setMethod(method);
                }
            }

        });
    }


    /**
     * 实行方法，(有返回值)
     */
    public Object getResultByName(reflectEntity ref) {
        try {
            Object o = ref.getMethod().invoke(ref.getClazz(), ref.getCondition().toArray());
            return o;
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            log.error("方法反射执行失败",e);
            return null;
        }

    }

    /**
     * 实行方法，(无返回值)
     */
    public void getNoneByName(reflectEntity ref) {
        try {
            ref.getMethod().invoke(ref.getClazz(), ref.getCondition().toArray());
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("方法反射执行失败",e);
            e.printStackTrace();
        }
    }


    public List<reflectEntity> getReflectList() {
        return reflectList;
    }

    public void setReflectList(List<reflectEntity> reflectList) {
        this.reflectList = reflectList;
    }


    /**
     * 实例化转成对象
     * 如果不是spring的启动可以newInstance实例化对象
     * 通过springcontext获取bean的方式获取service的class
     *
     * @param name
     * @return class
     */
    @Override
    public Object getThisClazz(String name) {
        return ApplicationContextProvider.getBean(name);
    }

    @Override
    public Object getThisClazz(Class z) {
        return ApplicationContextProvider.getBean(z);
    }

    @Override
    public void setSuperClazz(Class t) {
        setPrepareClass(t);
    }



    private void setPrepareClass(Class prepareClass) {
        this.prepareClass = prepareClass;
    }

    private  Class getPrepareClass() {
        return prepareClass;
    }
}
