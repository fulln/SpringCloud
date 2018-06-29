package com.fulln.apimail.entity;

public class EmailEntity {

    /**
     * 收件人
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
     * 地址 (图片或者文件或者其他)
     */
    private String path;

    /**
     * 判断是图片还是附件还是网页
     */
    private Integer flag;


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
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
