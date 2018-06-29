package com.fulln.config.util;

import java.util.*;

/**
 * @program: SpringCloud
 * @description: 继承properties，读取properties文件时按序加载
 * @author: fulln
 * @create: 2018-06-22 10:28
 **/
public class OrderdProperties extends Properties{

    private static final long serialVersionUID = -6013677912394570002L;

    private final LinkedHashSet<Object> keys = new LinkedHashSet<Object>();

    public Enumeration<Object> keys() {
        return Collections.<Object> enumeration(keys);
    }

    public Object put(Object key, Object value) {
        keys.add(key);
        return super.put(key, value);
    }

    public Set<Object> keySet() {
        return keys;
    }

    public Set<String> stringPropertyNames() {
        Set<String> set = new LinkedHashSet<String>();
        for (Object key : this.keys) {
            set.add((String) key);
        }
        return set;
    }

}
