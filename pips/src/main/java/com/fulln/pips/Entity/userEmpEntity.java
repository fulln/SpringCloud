package com.fulln.pips.Entity;


import com.fulln.pips.Common.util.CommonEnum;
import com.fulln.pips.Entity.baseEntity.BaseEntity;



public class userEmpEntity extends BaseEntity {


    private static final long serialVersionUID = -4085792688204033389L;

    private Integer id;
    private String username;
    private String password;
    private CommonEnum.BasicEntityEnum sex;
    private CommonEnum.BasicEntityEnum permission;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CommonEnum.BasicEntityEnum getSex() {
        return sex;
    }

    public void setSex(CommonEnum.BasicEntityEnum sex) {
        this.sex = sex;
    }

    public CommonEnum.BasicEntityEnum getPermission() {
        return permission;
    }

    public void setPermission(CommonEnum.BasicEntityEnum permission) {
        this.permission = permission;
    }

    public userEmpEntity(Integer id) {
        this.id = id;
    }
}
