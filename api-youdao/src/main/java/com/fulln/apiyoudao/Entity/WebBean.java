package com.fulln.apiyoudao.Entity;

import java.util.List;

/**
 * @Author: fulln
 * @Description:
 * @Date: Created in 2018/5/15 0015
 */
public class WebBean {


    private String key;
    private List<String> value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }
}