package com.fulln.webflux.config;

import com.fulln.webflux.controller.EchoHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: fulln
 * @Description:
 * @Date: Created in 2018/6/4 0004
 */
@Configuration
public class WebSocketConfiguration {

    @Autowired
    @Bean
    public HandlerMapping webSocketMapping(final EchoHandle echoHandle){
        final Map<String,WebSocketHandler> map =  new HashMap<>();
        map.put("/echo",echoHandle);
        final SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
        mapping.setUrlMap(map);
        return mapping;
    }

    @Bean
    public WebSocketHandlerAdapter handlerAdapter(){
        return new WebSocketHandlerAdapter();
    }

}
