package com.fulln.streamrecevier.entity;

import java.io.Serializable;


/**
 * @author Administrator
 */
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -3258839839160856613L;
    private int id;
    private String userName;
    private String passWord;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public UserEntity(int id, String userName, String passWord) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
    }

    public UserEntity() {
    }
}