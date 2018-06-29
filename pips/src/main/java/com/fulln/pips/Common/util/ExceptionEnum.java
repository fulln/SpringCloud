package com.fulln.pips.Common.util;

import com.fulln.pips.Common.BaseResult.GlobalResult;

/**
 * @Author: fulln
 * @Description:
 * @Date: Created in 2018/6/11 0011
 */
public enum ExceptionEnum  {

    //正常返回的枚举
    SUCCESS(true, 2000, "正常返回", "操作成功"),
    // 系统错误，50开头
    SYS_ERROR(false, 5000, "系统错误", "亲，系统出错了哦~"),
    PARAM_INVILAD(false, 5001, "参数出现异常", "参数出现异常"),
    DATA_NO_COMPLETE(false, 5002, "数据填写不完整，请检查", "数据填写不完整，请检查");


    ExceptionEnum(boolean ok, int code, String msg, String userMsg) {
        this.ok = ok;
        this.code = code;
        this.label = msg;
        this.userMsg = userMsg;
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
