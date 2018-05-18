package com.fulln.config.entity;

import java.lang.reflect.Method;
import java.util.Map;

public class ThreadEntity{

    private String MethodName;  
      
    private Class clazz;
      
    private Map<String, Object> condition;
      
    public String getMethodName() {  
        return MethodName;  
    }  
    public void setMethodName(String methodName) {  
        MethodName = methodName;  
    }  
    public Class getClazz() {
        return clazz;
    }  
    public void setClazz(Class clazz) {
        this.clazz = clazz;  
    }  
    public Map<String, Object> getCondition() {  
        return condition;  
    }  
    public void setCondition(Map<String, Object> condition) {  
        this.condition = condition;  
    }

    public ThreadEntity(
            String methodName, Class clazz, Map<String, Object> condition) {
        MethodName = methodName;
        this.clazz = clazz;
        this.condition = condition;
    }
}