package com.fulln.streamsender.listener;

import com.fulln.streamsender.entity.UserEntity;
import com.fulln.streamsender.sinkreplay.RecordingChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * @program: SpringCloud
 * @description: 当前的监听
 * @author: fulln
 * @create: 2018-08-06 16:04
 * @Version： 0.0.1
 **/
//@EnableBinding(value = {RecordingChannel.class})
@Slf4j
public class TimeListener {

    /**
     * 回复消息的监听
     */
    @StreamListener(RecordingChannel.REPLAY_SINK_CHANNEL)
    public void recvierSend(@Payload UserEntity user) {
        log.info("Received from {} channel age: {}", RecordingChannel.REPLAY_SINK_CHANNEL, user.getId());
    }
}
