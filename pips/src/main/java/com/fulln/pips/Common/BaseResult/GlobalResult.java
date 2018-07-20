package com.fulln.pips.Common.BaseResult;


/**
 * @Author: fulln
 * @Description: 通用当前返回结果
 * @Date: Created in 2018/5/2 0002
 */
public class GlobalResult {

    private Boolean resultsuccess;

    private String  message;

    private Integer code;

    private String umessage;

    private Object datas;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getUmessage() {
        return umessage;
    }

    public void setUmessage(String umessage) {
        this.umessage = umessage;
    }

    public Boolean getResultSuccess(Boolean ok) {
        return resultsuccess;
    }

    public void setResultSuccess(Boolean resultSuccess) {
        this.resultsuccess = resultSuccess;
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
        this.resultsuccess = resultSuccess;
        this.message = message;
        this.datas = datas;
    }

    public GlobalResult() {

    }
}
