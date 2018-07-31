package com.fulln.webfluxparadigm.handle;

import com.fulln.webfluxparadigm.dao.UserDao;
import com.fulln.webfluxparadigm.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import java.util.Optional;


/**
 * @program: SpringCloud
 * @description: 进行MongoDb的操作
 * @author: fulln
 * @create: 2018-07-30 17:38
 * @Version： 0.0.1
 **/
@Component
public class WebSelectHandle {

    @Autowired
    private UserDao userDao;

    private Mono<ServerResponse> selectAll(final ServerRequest request) {
        return ServerResponse
                .ok()
                .body(Mono.justOrEmpty(Optional.ofNullable(userDao.findUserByUserName("test"))), UserEntity.class)
                ;
    }


}
