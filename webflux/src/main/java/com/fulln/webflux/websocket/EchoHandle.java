package com.fulln.webflux.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

/**
 * @Author: fulln
 * @Description: websocket 方式进行数据传递
 * @Date: Created in 2018/6/4 0004
 */
@Component
public class EchoHandle implements WebSocketHandler {

    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {

        return webSocketSession.send(webSocketSession.receive()
            .map(msg ->webSocketSession.textMessage("ECHO ->"+msg.getPayloadAsText()))
        );
    }


}
