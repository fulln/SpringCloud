package com.fulln.apimail.exception;


import com.fulln.apimail.enums.ExceptionEnum;

/**
 * @AUthor: fulln
 * @Description:自定义异常类
 * @Date : Created in  16:17  2018/5/20.
 */
public class CustomException extends Exception {

    private static final long serialVersionUID = 4322129956631706005L;

    private ExceptionEnum errorMsgEnum;

    private Integer errorCode;
    private String errorMessage;

    public CustomException(ExceptionEnum errorMsgEnum) {
        super();
        this.errorMsgEnum = errorMsgEnum;
        this.errorCode = errorMsgEnum.getValue();
        this.errorMessage = errorMsgEnum.getLabel();
    }

    public ExceptionEnum getErrorMsgEnum() {
        return errorMsgEnum;
    }

    public void setErrorMsgEnum(ExceptionEnum errorMsgEnum) {
        this.errorMsgEnum = errorMsgEnum;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}