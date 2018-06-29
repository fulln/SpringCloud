package com.fulln.pips.Common.Exception;

import com.fulln.pips.Common.util.ExceptionEnum;

/**
 * @AUthor: fulln
 * @Description:自定义异常类
 * @Date : Created in  16:17  2018/5/20.
 */
public class CustomException extends Exception {

    private static final long serialVersionUID = 4322129956631706005L;

    private  ExceptionEnum errorMsgEnum;


    public ExceptionEnum getErrorMsgEnum() {

        return errorMsgEnum;
    }

    public void setErrorMsgEnum(ExceptionEnum errorMsgEnum) {
        this.errorMsgEnum =errorMsgEnum ;
    }

}