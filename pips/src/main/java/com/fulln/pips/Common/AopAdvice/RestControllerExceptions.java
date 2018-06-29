package com.fulln.pips.Common.AopAdvice;

import com.fulln.pips.Common.BaseResult.GlobalResult;
import com.fulln.pips.Common.Exception.CustomException;
import com.fulln.pips.Common.util.ExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: fulln
 * @Description:controller层统一的异常处理类
 * @Date: Created in 2018/6/11 0011
 */
@Slf4j
@RestControllerAdvice
public class RestControllerExceptions {

    @ExceptionHandler(value = {CustomException.class})
    public GlobalResult unCatchedException(CustomException e){
        log.error("[controller]控制层中未捕捉的异常");
        return ExceptionEnum.SYS_ERROR.globalResult();
    }

}
