package com.fulln.config.api;

/**
 * @Author: fulln
 * @Description:获取指定class
 * @Date: Created in 2018/6/9 0009
 */
public interface getAbstractClass {

    Object getThisClazz(String t);

    Object getThisClazz(Class t);

    void setSuperClazz(Class t);


}
