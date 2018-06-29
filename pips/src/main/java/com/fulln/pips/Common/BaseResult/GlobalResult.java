package com.fulln.pips.Common.BaseResult;


import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: fulln
 * @Description: 通用当前返回结果
 * @Date: Created in 2018/5/2 0002
 */
public class GlobalResult {

    private Boolean ResultSuccess;

    private String  message;

    private Integer code;

    private String Umessage;

    private Object datas;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getUmessage() {
        return Umessage;
    }

    public void setUmessage(String umessage) {
        Umessage = umessage;
    }

    public Boolean getResultSuccess(Boolean ok) {
        return ResultSuccess;
    }

    public void setResultSuccess(Boolean resultSuccess) {
        ResultSuccess = resultSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDatas() {
        return datas;
    }

    public void setDatas(Object datas) {
        this.datas = datas;
    }

    GlobalResult(Boolean resultSuccess, String message, Object datas) {
        ResultSuccess = resultSuccess;
        this.message = message;
        this.datas = datas;
    }

    public GlobalResult() {

    }
}
