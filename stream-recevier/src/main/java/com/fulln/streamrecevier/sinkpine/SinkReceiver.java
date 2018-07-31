package com.fulln.streamrecevier.sinkpine;

import com.fulln.streamrecevier.channel.ChannelReceiver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;


/**
 * @program: SpringCloud
 * @description: 默认的消息订阅通道
 * @author: fulln
 * @create: 2018-07-31 14:14
 * @Version： 0.0.1
 **/
@Slf4j
@EnableBinding(value = {Sink.class,ChannelReceiver.class})
public class SinkReceiver {

        @StreamListener(ChannelReceiver.CHANNEL)
//    @StreamListener(Sink.INPUT)
    public void receive(Object payload) {
        log.info("Received from default channel : {}",payload.toString());
//        log.info("Received from default channel : {}",payload.toString());
    }

}
