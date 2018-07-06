package com.fulln.apimail.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 邮件entity
 * @author Administrator
 */
public class EmailEntity implements Serializable {

    private static final long serialVersionUID = 1785196060454970354L;


    /**
     * 收件人 (多人以,隔开)
     */
	private String receiver;
    /**
     * 标题
     */
    private String subject;
    /**
     * 模板
     */
    private String text;
    /**
     * 添加抄送人 (多人以,隔开)
     */
    private String ccUser;
    /**
     * 密送人(多人以,隔开)
     */
    private String bccUser;
    /**
     * 附件地址
     */
    private String[] attachment;

    /**
     * 邮件中发送的照片地址
     */
    private String imgPath;
    /**
     * excel的处理类
     */
    private List<ExcelEntity> excelEntityList;


    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getCcUser() {
        return ccUser;
    }

    public void setCcUser(String ccUser) {
        this.ccUser = ccUser;
    }

    public String getBccUser() {
        return bccUser;
    }

    public void setBccUser(String bccUser) {
        this.bccUser = bccUser;
    }

    public String[] getAttachment() {
        return attachment;
    }

    public void setAttachment(String[] attachment) {
        this.attachment = attachment;
    }



    public List<ExcelEntity> getExcelEntityList() {
        return excelEntityList;
    }

    public void setExcelEntityList(List<ExcelEntity> excelEntityList) {
        this.excelEntityList = excelEntityList;
    }

    public String getReceiver() {
        return receiver;
    }
 
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
 
    public String getSubject() {
        return subject;
    }
 
    public void setSubject(String subject) {
        this.subject = subject;
    }
 
    public String getText() {
        return text;
    }
 
    public void setText(String text) {
        this.text = text;
    }

}
