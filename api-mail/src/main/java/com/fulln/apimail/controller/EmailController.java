package com.fulln.apimail.controller;

import com.fulln.apimail.entity.EmailEntity;
import com.fulln.apimail.service.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: SpringCloud
 * @description: 邮件发送
 * @author: fulln
 * @create: 2018-06-29 11:52
 **/
@Slf4j
@RestController
@RequestMapping("/sendMail")
@Api("发送邮件接口")
public class EmailController {

    @Resource
    private MailService mailService;

    @PostMapping("/qq")
    @ApiOperation("给qq邮箱发送邮件")
    @ApiResponses({
                     @ApiResponse(code=400,message="请求参数没填好"),
                     @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
                 })
    private void sendToSomeone(){
        EmailEntity emailEntity = new EmailEntity();
        emailEntity.setReceiver("a114mypr1nt@gmail.com");
        emailEntity.setSubject("myself");
        emailEntity.setText("<html><head></head><body><h1>测试附件资料</h1></body></html>");
        mailService.sendFtpMail(emailEntity);
        log.info("发送完成");
    }

}
