package com.fulln.apimail.controller;

import com.fulln.apimail.baseResult.GlobalResult;
import com.fulln.apimail.entity.EmailEntity;
import com.fulln.apimail.service.MailService;
import io.swagger.annotations.*;
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "receiver",value = "收件人",required = true),
            @ApiImplicitParam(name = "subject",value = "标题",required = true),
            @ApiImplicitParam(name = "text",value = "正文",required = true),
            @ApiImplicitParam(name = "ccUser",value = "添加抄送人"),
            @ApiImplicitParam(name = "bccUser",value = "密送人"),
            @ApiImplicitParam(name = "attachment",value = "附件地址",dataType = "String[]"),
            @ApiImplicitParam(name = "imgPath",value = "照片地址")
    })
    private GlobalResult sendToSomeone(EmailEntity emailEntity){
        return mailService.sendHtmlMail(emailEntity);
    }

}
