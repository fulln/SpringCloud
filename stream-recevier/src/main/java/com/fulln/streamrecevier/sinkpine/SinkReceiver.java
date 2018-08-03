package com.fulln.streamrecevier.sinkpine;

import com.fulln.streamrecevier.channel.ChannelReceiver;
import com.fulln.streamrecevier.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


/**
 * @program: SpringCloud
 * @description: 默认的消息订阅通道
 * @author: fulln
 * @create: 2018-07-31 14:14
 * @Version： 0.0.1
 **/
@Slf4j
@EnableBinding(value = {ChannelReceiver.class})
public class SinkReceiver {

    @StreamListener(value = ChannelReceiver.CHANNEL,condition = "headers['name'] == 'lea'")
    public void receive(@Payload UserEntity userEntity, @Headers Map heads) {
        log.error(heads.get("contentType").toString());
        log.info(heads.get("name").toString());
        log.info("Received from default channel : {}", userEntity.getUserName());
    }

    @StreamListener(value = ChannelReceiver.CHANNEL,condition = "headers['name'] == 'lee'")
    public void receiveBy(@Payload UserEntity userEntity, @Headers Map heads) {
        log.error(heads.get("contentType").toString());
        log.info(heads.get("name").toString());
        log.info("Received from default channel : {}", userEntity.getUserName());
    }

    @StreamListener(ChannelReceiver.OUTPUT1_CHANNEL)
    public void myReceive1(@Payload String  payloads) {
        log.info("Received from {} channel : {}", ChannelReceiver.OUTPUT1_CHANNEL, payloads);
    }

    @StreamListener(ChannelReceiver.OUTPUT2_CHANNEL)
    public void myReceive2(Object payload) {
        log.info("Received from {} channel : {}", ChannelReceiver.OUTPUT2_CHANNEL, new String((byte[])payload));
    }

    @ServiceActivator(inputChannel = ChannelReceiver.ORIGINAL_CHANNEL)
    public void orginalReciver(Object payload){
        log.info("Received from {} channel : {}", ChannelReceiver.ORIGINAL_CHANNEL,payload.toString());
    }


    @Transformer(inputChannel = ChannelReceiver.ORIGINAL_CHANNEL,outputChannel = ChannelReceiver.ORIGINAL_CHANNEL)
    public Object transform(Date message){
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(message);
    }

    @StreamListener(ChannelReceiver.REPLAY_SOURCE_CHANNEL)
    @SendTo(ChannelReceiver.REPLAY_SINK_CHANNEL)
    public UserEntity replayUser(@Payload UserEntity user) {
        log.info("Received from {} channel age: {}", ChannelReceiver.REPLAY_SOURCE_CHANNEL, user.getId());
        user.setId(user.getId() + 1);
        return user;
    }
}
