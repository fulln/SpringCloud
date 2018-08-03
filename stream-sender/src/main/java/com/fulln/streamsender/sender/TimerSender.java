package com.fulln.streamsender.sender;

import com.fulln.streamsender.entity.UserEntity;
import com.fulln.streamsender.sinkreplay.RecordingChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.GenericMessage;

/**
 * @program: SpringCloud
 * @description: 时间的发送轮询
 * @author: fulln
 * @create: 2018-08-02 13:37
 * @Version： 0.0.1
 **/
@EnableBinding(RecordingChannel.class)
@Slf4j
public class TimerSender {

    /**
     * 用户的发送轮询
     * @return
     */
    @Bean
    @InboundChannelAdapter(value = RecordingChannel.REPLAY_SOURCE_CHANNEL, poller = @Poller(fixedRate = "4000", maxMessagesPerPoll = "1"))
    public MessageSource<UserEntity> timemessageSender() {

        return () -> new GenericMessage<>(new UserEntity(1, "lea", "123456"));
    }

    /**
     * 回复消息的监听
     */
    @StreamListener(RecordingChannel.REPLAY_SINK_CHANNEL)
    public void recvierSend(@Payload UserEntity user){
        log.info("Received from {} channel age: {}", RecordingChannel.REPLAY_SINK_CHANNEL, user.getId());
    }

}
