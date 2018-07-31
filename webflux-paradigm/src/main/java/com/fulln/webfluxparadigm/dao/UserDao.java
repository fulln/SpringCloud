package com.fulln.webfluxparadigm.dao;

import com.fulln.webfluxparadigm.entity.UserEntity;

public interface UserDao{

    void saveUser(UserEntity user);

    UserEntity findUserByUserName(String userName);

    void updateUser(UserEntity user);

    public void deleteUserById(int id);

}
