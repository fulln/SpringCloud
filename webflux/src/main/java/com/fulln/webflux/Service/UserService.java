package com.fulln.webflux.Service;

import com.fulln.webflux.POJO.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Author: fulln
 * @Description:
 * @Date: Created in 2018/6/4 0004
 */
public interface UserService {

    Flux<User> list();

    Flux<User> getById(final Flux<String> ids);

    Mono<User> getById(final String id);

    Flux<User> createOrUpdate(final Flux<User> users);

    Mono<User> createOrUpdate(final User user);

    Mono<User> delete(final String id);


}
