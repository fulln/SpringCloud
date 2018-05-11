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

    private Object datas;

    private PageInfo datas2;



    public Boolean getResultSuccess() {
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

    public PageInfo getDatas2() {
        return datas2;
    }

    public void setDatas2(PageInfo datas2) {
        this.datas2 = datas2;
    }

    public GlobalResult(Boolean resultSuccess, String message, Object datas) {
        ResultSuccess = resultSuccess;
        this.message = message;
        this.datas = datas;
    }

    public GlobalResult(Boolean resultSuccess, String message,PageInfo datas2) {
        ResultSuccess = resultSuccess;
        this.message = message;
        this.datas2 = datas2;
    }

    public GlobalResult() {
    }

    //success
    public static  GlobalResult getSuccessResult(Object data) {

        return getSuccessResult(data, null);

    }

    //success with list
    public static  GlobalResult getSuccessResult(PageInfo data) {

        return getSuccessResult(data, null);

    }

    //success only message
    public static  GlobalResult getSuccessResult(String message) {

        return getSuccessResult(null, message);

    }

    //success with message and data
    public static GlobalResult getSuccessResult(Object data, String Message) {

        return new  GlobalResult(true, Message, data);

    }

    // success with list
    public static GlobalResult getSuccessResult(PageInfo data, String Message) {

        return new  GlobalResult(true, Message, data);

    }

    //fault with code
    public static GlobalResult getfaultResult(Exception e) {

        return getfaultResult(e.getMessage());

    }

    //fault only message
    public static  GlobalResult getfaultResult(String Message) {

        return new  GlobalResult(false, Message, null);

    }


}
