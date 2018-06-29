package com.fulln.pips.Common.AopAdvice;


import com.fulln.pips.Common.BaseResult.GlobalResult;
import com.fulln.pips.Common.Exception.CustomException;
import com.fulln.pips.Common.util.ExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * @Author: fulln
 * @Description:
 * @Date: Created in 2018/5/2 0002
 */
@Aspect
@Component
@Slf4j
public class AspectScan {

    //AfterThrowing异常发生后能执行的方法  但是他并不能阻止异常的抛出或者处理
    /*@AfterThrowing(pointcut = "gethandle()", throwing = "e")
    public GlobalResult AfterThrows(JoinPoint jp, Throwable e) {
        log.error("ExceptionHandler : ", e);
        if (e instanceof IllegalArgumentException) {
            return getfaultResult(e.getMessage());
        } else if (e instanceof NullPointerException) {
            return getfaultResult(e.getMessage());
        } else if (e instanceof BadSqlGrammarException) {
            return getfaultResult("内部错误！请联系管理员！");
        } else {
            return getfaultResult("系统异常！");
        }
    }*/


    @Pointcut("@within(org.springframework.stereotype.Service) && execution(* com.fulln.pips.Service.*.*(..))")
    public void gethandle() {
    }


    @Before("gethandle()")
    public void before(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();

        String entity = joinPoint.getTarget().getClass().getName();
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取请求的request
        HttpServletRequest request = attributes.getRequest();

        //获取请求的URL
        StringBuffer requestURL = request.getRequestURL();
        //获取参 数信息
        String queryString = request.getQueryString();

        //封装完整请求URL带参数
        if (queryString != null) {
            requestURL.append("?").append(queryString);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("收到请求!!!\r\n");
        sb.append("   请求URL：").append(requestURL).append("\r\n");
        sb.append("   接口方法：").append(entity).append(".").append(methodName).append("\r\n");
        log.info(sb.toString());
    }

    @Around("gethandle()")
    public GlobalResult aroundService(ProceedingJoinPoint pJoinPoint ){
        GlobalResult result = new GlobalResult();
        try {
            result = (GlobalResult) pJoinPoint.proceed();
        } catch (CustomException e){
            log.error("[aop]系统异常",e);
            result = ExceptionEnum.SYS_ERROR.globalResult();
        } catch (Throwable e) {
            log.error("ExceptionHandler : ", e);
            if (e instanceof IllegalArgumentException) {
                result.setMessage(e.getMessage());
            } else if (e instanceof NullPointerException) {
                result.setMessage(e.getMessage());
            } else if (e instanceof BadSqlGrammarException) {
                result.setMessage("内部错误！请联系管理员！");
            } else {
                result.setMessage("系统异常！");
            }
        }
        return  result;
    }


}
