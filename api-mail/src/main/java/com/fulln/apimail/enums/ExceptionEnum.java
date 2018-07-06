package com.fulln.apimail.enums;

import com.fulln.apimail.baseResult.GlobalResult;

/**
 * @Author: fulln
 * @Description:
 * @Date: Created in 2018/6/11 0011
 */
public enum ExceptionEnum implements DisplayedEnum {

    //正常返回的枚举
    SUCCESS(true, 2000, "正常返回", "操作成功"),
    SEND_SUCCESS(true,2004,"邮件发送成功","邮件发送成功"),
    // 系统错误，50开头
    SYS_ERROR(false, 5000, "系统错误", "亲，系统出错了哦~"),
    PARAM_INVILAD(false, 5001, "参数出现异常", "参数出现异常"),
    DATA_NO_COMPLETE(false, 5002, "数据填写不完整，请检查", "数据填写不完整，请检查"),
    SEND_FAILT(false,5003,"邮件发送失败","邮件发送失败"),
    USER_FAILT(false,5002,"接收人不能为空","接收人不能为空");


    ExceptionEnum(boolean ok, int code, String msg, String userMsg) {
        this.ok = ok;
        this.code = code;
        this.label = msg;
        this.userMsg = userMsg;
    }

    ExceptionEnum(int code,String msg){
        this.code=code;
        this.label=msg;
    }

    private boolean ok;
    private int code;
    private String label;
    private String userMsg;

    public GlobalResult globalResult(Object... datas){
        GlobalResult g =new GlobalResult();
        g.setMessage(label);
        g.setDatas(datas);
        g.getResultSuccess(ok);
        g.setCode(code);
        return g;
    }




}
