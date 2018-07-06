package com.fulln.apimail.service.impl;

import com.alibaba.fastjson.JSON;
import com.fulln.apimail.baseResult.GlobalResult;
import com.fulln.apimail.entity.EmailEntity;
import com.fulln.apimail.entity.ExcelEntity;
import com.fulln.apimail.entity.UserCell;
import com.fulln.apimail.enums.ExceptionEnum;
import com.fulln.apimail.service.MailService;
import com.fulln.apimail.utils.ExcelUtils;
import com.fulln.apimail.utils.FileUtils;
import com.fulln.apimail.utils.PicUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.*;

/**
 * @program: SpringCloud
 * @description: 邮件发送实现类
 * @author: fulln
 * @create: 2018-06-29 11:39
 **/
@Service("MailService")
@Slf4j
public class MailServiceImpl implements MailService {

    @Value("${stmp.host}")
    private String host;
    @Value("${stmp.account}")
    private String account;
    @Value("${stmp.password}")
    private String password;
    @Value("${mail.smtp.auth}")
    private String isAuth;
    @Value("${mail.smtp.timeout}")
    private String outTime;
    @Value("${mail.imgPath}")
    private String imgPath;


    /**
     * @Author: fulln
     * @Description:邮件发送业务处理
     * @param: [email]
     * @return: com.fulln.apimail.baseResult.GlobalResult
     * @Date: 2018/7/6 0006-13:33
     */
    @Override
    public GlobalResult sendHtmlMail(EmailEntity email) {

        JavaMailSenderImpl sender = getMailSender();
        // 建立邮件消息,发送简单邮件和html邮件的区别
        MimeMessage mailMessage = sender.createMimeMessage();
        try {
            email = handleEntity(email);
            // 注意这里的boolean,等于真的时候才能嵌套图片，在构建MimeMessageHelper时候，所给定的值是true表示启用，
            // multipart模式 为true时发送附件 可以设置html格式
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");

            messageHelper.setFrom(account);

            // 设置收件人
            if (email.getReceiver() != null) {
                String receiver = email.getReceiver();
                String[] receivers = receiver.split(",");
                messageHelper.setTo(receivers);
            }

            //设置主题
            messageHelper.setSubject(email.getSubject());

            //设置抄送地址
            if (email.getCcUser() != null) {
                InternetAddress[] ccAddress = InternetAddress.parse(email.getCcUser());
                messageHelper.setCc(ccAddress);
            }

            //设置密送地址
            if (email.getBccUser() != null) {
                InternetAddress[] bccAddress = InternetAddress.parse(email.getBccUser());
                messageHelper.setCc(bccAddress);
            }

            //发送日期
            messageHelper.setSentDate(new Date());




            //添加附件
            if (email.getAttachment() != null && email.getAttachment().length != 0) {

                for (String f : email.getAttachment()
                        ) {
                    FileSystemResource file = new FileSystemResource(new File(f));
                    String[] paths = f.split("\\\\|/");
                    String fileName = paths[paths.length - 1];
                    messageHelper.addAttachment(fileName, file);
                }
            }
            // true 表示启动HTML格式的邮件 发送正文
            messageHelper.setText(
                    "<html><head></head><body>"+
                            email.getText()
                            + "<img src=\"cid:pic\">"
                            + "</body></html>"
                    , true);

            //正文中插入图片
            if (email.getImgPath() != null) {
                FileSystemResource img = new FileSystemResource(new File(email.getImgPath()));
                messageHelper.addInline("pic", img);
            }

            // 发送邮件
            sender.send(mailMessage);
            return ExceptionEnum.SEND_SUCCESS.globalResult();
        } catch (MessagingException | InvalidFormatException | IOException e) {
            log.error("邮件发送", e);
            e.printStackTrace();
            return ExceptionEnum.SEND_FAILT.globalResult();
        } catch (Exception e) {
            log.error("系统异常", e);
            e.printStackTrace();
            return ExceptionEnum.SYS_ERROR.globalResult();
        }
    }

    /**
     * @Author: fulln
     * @Description: 对邮件实体类的处理, (是否带附件, 是否带图片)
     * @param: [email]
     * @return: com.fulln.apimail.entity.EmailEntity
     * @Date: 2018/7/6 0006-10:22
     */
    @Override
    public EmailEntity handleEntity(EmailEntity email) throws Exception {

        //周报excel的默认地址
        if (email.getExcelEntityList() == null) {

            //默认的是从json文件中获取当前需要更改的部分

            String value = FileUtils.readFromJSON("excelChange.json");
            if(value != null){
                email.setExcelEntityList(JSON.parseArray(value,ExcelEntity.class));
            }

            FileUtils fileUtils = new FileUtils("application.properties");
            String name= fileUtils.getProperty("mail.filePath");


            email.setAttachment(new String[]{ExcelUtils.updateExcels(name, email.getExcelEntityList())});
        }
        //图片默认地址
        if (email.getImgPath() == null) {
            List<ExcelEntity> li = new ArrayList<>();
            ExcelEntity entity = new ExcelEntity();
            //可以自行指定excel地址
            entity.setExcelPath(email.getAttachment()[0]);
            li.add(entity);
            //默认的图片地址
            String path = PicUtils.getPrintscreen(li);
            email.setImgPath(path);
        }
        return email;
    }

    /**
     * @Author: fulln
     * @Description:初始化邮件发送者
     * @param: []
     * @return: org.springframework.mail.javamail.JavaMailSenderImpl
     * @Date: 2018/7/6 0006-11:57
     */
    private JavaMailSenderImpl getMailSender() {

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setUsername(account);
        javaMailSender.setPassword(password);
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", isAuth);
        properties.put("mail.smtp.timeout", outTime);
        javaMailSender.setJavaMailProperties(properties);
        return javaMailSender;
    }


}
