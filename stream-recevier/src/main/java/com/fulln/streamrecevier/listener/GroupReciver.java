package com.fulln.streamrecevier.listener;

import com.fulln.streamrecevier.channel.MySink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * @program: SpringCloud
 * @description: channel监听
 * @author: fulln
 * @create: 2018-08-07 09:49
 * @Version： 0.0.1
 **/
@Slf4j
@EnableBinding(value = {MySink.class})
public class GroupReciver {

    @Value("${spring.profiles.active:0}")
    private String active;

    @StreamListener(value = MySink.GROUP_CHANNEL)
    public void groupReceiver(@Payload String payload) {
        log.info("Received-{} from {} channel payload: {}", active, MySink.GROUP_CHANNEL, payload);
    }


}
