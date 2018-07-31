package com.fulln.webfluxparadigm.dao;

import java.util.List;

public interface MongoBaseDao<T> {

    void insertOne(T t);

    void insertList(List<T> list) throws Exception;

    void deleteById(String id) throws Exception;

    void deleteAll() throws Exception;

    List<T> findAll() throws Exception;

    T findById(String id);

}
