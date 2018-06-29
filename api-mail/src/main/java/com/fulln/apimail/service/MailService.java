package com.fulln.apimail.service;

import com.fulln.apimail.entity.EmailEntity;

/**
 * @program: springcloud
 * @description: 邮件发送
 * @author: fulln
 * @create: 2018-06-29 11:32
 **/
public interface MailService {

    /**
     * 发送邮件
     * @param email
     */
    void sendSimpleMail(EmailEntity email);

    /**
     * 发送带附件的邮件
     * @param email
     */
    void sendFtpMail(EmailEntity email);



}
