package com.fulln.webfluxparadigm.dao.impl;

import com.fulln.webfluxparadigm.dao.UserDao;
import com.fulln.webfluxparadigm.entity.UserEntity;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserDaoImpl  implements UserDao {


    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 创建对象
     * @param user
     */
    @Override
    public void saveUser(UserEntity user) {
        try {
            mongoTemplate.save(user);
        }catch (Exception  e){
            e.printStackTrace();
        }
    }

    /**
     * 根据用户名查询对象
     * @param userName
     * @return
     */
    @Override
    public UserEntity findUserByUserName(String userName) {
        Query query=new Query(Criteria.where("userName").is(userName));
        UserEntity user = null;
        try {
            user=  mongoTemplate.findOne(query , UserEntity.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 更新对象
     * @param user
     */

    @Override
    public void updateUser(UserEntity user) {
        Query query=new Query(Criteria.where("id").is(user.getId()));
        Update update= new Update().set("userName", user.getUserName()).set("passWord", user.getPassWord());
        //更新查询返回结果集的第一条
        try{
            mongoTemplate.updateFirst(query,update,UserEntity.class);
        }catch (Exception e){
            e.printStackTrace();
        }

        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);
    }

    /**
     * 删除对象
     * @param id
     */
    @Override
    public void deleteUserById(int id) {
        Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,UserEntity.class);
    }
}