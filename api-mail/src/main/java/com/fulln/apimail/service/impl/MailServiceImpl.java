package com.fulln.apimail.service.impl;

import com.fulln.apimail.entity.EmailEntity;
import com.fulln.apimail.service.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;

/**
 * @program: SpringCloud
 * @description: 邮件发送实现类
 * @author: fulln
 * @create: 2018-06-29 11:39
 **/
@Service("MailService")
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
    @Value("${mail.filePath}")
    private String filePath;

    @Override
    public void sendSimpleMail(EmailEntity email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(account);
        String receiver = email.getReceiver();
        String[] receivers = receiver.split(";");
        simpleMailMessage.setTo(receivers);
        simpleMailMessage.setSubject(email.getSubject());
        simpleMailMessage.setText(email.getText());
        getMailSender().send(simpleMailMessage);
    }


    /**
     * 发送邮件
     *
     * @param email
     */
    @Override
    public void sendFtpMail(EmailEntity email) {
        JavaMailSenderImpl sender = getMailSender();
        // 建立邮件消息,发送简单邮件和html邮件的区别
        MimeMessage mailMessage = sender.createMimeMessage();
        try {
            // 注意这里的boolean,等于真的时候才能嵌套图片，在构建MimeMessageHelper时候，所给定的值是true表示启用，
            // multipart模式 为true时发送附件 可以设置html格式
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");

            String receiver = email.getReceiver();
            String[] receivers = receiver.split(";");
            // 设置收件人，寄件人
            messageHelper.setTo(receivers);
            messageHelper.setFrom(account);
            messageHelper.setSubject(email.getSubject());

            switch (email.getFlag()) {
                case 1: //图片
                    FileSystemResource img = new FileSystemResource(new File("d:/Desert.jpg"));
                    messageHelper.addInline("pic", img);
                case 2: //附件
                    FileSystemResource file = new FileSystemResource(new File(filePath));
                    // 这里的方法调用和插入图片是不同的。
                    String[] paths = filePath.split("\\\\");
                    String fileName = paths[paths.length - 1];
                    messageHelper.addAttachment(fileName, file);
                    messageHelper.setText(email.getText(), true);
                case 3://添加抄送
                    //messageHelper.addBcc();

                default:
                    messageHelper.setText(email.getText());
            }
            // true 表示启动HTML格式的邮件
            messageHelper.setText(email.getText(), true);


            FileSystemResource file = new FileSystemResource(new File(filePath));
            // 这里的方法调用和插入图片是不同的。
            String[] paths = filePath.split("\\\\");
            String fileName = paths[paths.length - 1];
            messageHelper.addAttachment(fileName, file);
            // 发送邮件
            sender.send(mailMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

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
