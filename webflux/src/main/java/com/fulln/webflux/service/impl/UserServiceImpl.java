package com.fulln.webflux.service.impl;

import com.fulln.webflux.pojo.User;
import com.fulln.webflux.service.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: fulln
 * @Description:
 * @Date: Created in 2018/6/4 0004
 */
@Service
public class UserServiceImpl implements UserService{


    private final Map<String,User> data =  new ConcurrentHashMap<>();

    @Override
    public Flux<User> list() {
        return Flux.fromIterable(this.data.values());
    }

    @Override
    public Flux<User> getById(Flux<String> ids) {
        return ids.flatMap(id ->Mono.justOrEmpty(this.data.get(id)));
    }

    @Override
    public Mono<User> getById(String id) {
        return Mono.justOrEmpty(this.data.get(id)).switchIfEmpty(
                Mono.error(new Exception())
        );
    }

    @Override
    public Flux<User> createOrUpdate(Flux<User> users) {
        return users.doOnNext(user -> {
            this.data.put(user.getId()+"",user);
        });
    }

    @Override
    public Mono<User> createOrUpdate(User user) {
        this.data.put(user.getId()+"",user);
        return Mono.just(user);
    }

    @Override
    public Mono<User> delete(String id) {
        return Mono.justOrEmpty(this.data.remove(id));
    }
}
