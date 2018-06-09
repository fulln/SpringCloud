package com.fulln.config.entity;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

public class reflectEntity implements Serializable{

    private static final long serialVersionUID = -6465769192927540622L;

    private String MethodName;  //方法名

    private Method method;

    private Object clazz; //类
      
    private List<Object> condition; //参数(以map的形式)
      
    public String getMethodName() {  
        return MethodName;  
    }  
    public void setMethodName(String methodName) {  
        MethodName = methodName;

    }  
    public Object getClazz() {
        return clazz;
    }  
    public void setClazz(Object clazz) {
        this.clazz = clazz;  
    }

    public List<Object> getCondition() {
        return condition;
    }

    public void setCondition(List<Object> condition) {
        this.condition = condition;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public reflectEntity(String methodName, Object clazz, List<Object> condition) {
        MethodName = methodName;
        this.clazz = clazz;
        this.condition = condition;
    }

    public reflectEntity() {
    }
}