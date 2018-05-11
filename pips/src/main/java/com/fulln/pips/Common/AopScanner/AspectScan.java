package com.fulln.pips.Common.AopScanner;


import com.fulln.pips.Common.BaseResult.GlobalResult;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.fulln.pips.Common.BaseResult.GlobalResult.getfaultResult;


/**
 * @Author: fulln
 * @Description:
 * @Date: Created in 2018/5/2 0002
 */
@Aspect
@Component
public class AspectScan {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @AfterThrowing(pointcut = "gethandle()", throwing = "e")
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
    }

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
        StringBuffer sb = new StringBuffer();
        sb.append("收到请求!!!\r\n");
        sb.append("   请求URL：" + requestURL + "\r\n");
        sb.append("   接口方法：" + entity + "." + methodName + "\r\n");
        log.info(sb.toString());
    }


}
