package com.fulln.apimail.service;

import com.fulln.apimail.baseResult.GlobalResult;
import com.fulln.apimail.entity.EmailEntity;

/**
 * @program: springcloud
 * @description: 邮件发送
 * @author: fulln
 * @create: 2018-06-29 11:32
 **/
public interface MailService {





    /**
     * @Author: fulln
     * @Description 发送邮件
     * @para: email
     * @retun: a
     * @Date: 2018/7/6 0006-13:32
     */
    GlobalResult sendHtmlMail(EmailEntity email);



    /**
     * 对邮件附件的处理和其他的处理
     */
     EmailEntity handleEntity(EmailEntity email) throws Exception;

}
