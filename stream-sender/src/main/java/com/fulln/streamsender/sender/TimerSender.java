package com.fulln.streamsender.sender;

import com.fulln.streamsender.entity.UserEntity;
import com.fulln.streamsender.sinkreplay.GroupChannel;
import com.fulln.streamsender.sinkreplay.RecordingChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

/**
 * @program: SpringCloud
 * @description: 时间的发送轮询
 * @author: fulln
 * @create: 2018-08-02 13:37
 * @Version： 0.0.1
 **/
@EnableBinding(GroupChannel.class)
@Slf4j
public class TimerSender {

    private static int count = 0;
    /**
     * 用户的发送轮询
     *
     * @return
     */
//    @Bean
//    @InboundChannelAdapter(value = RecordingChannel.REPLAY_SOURCE_CHANNEL, poller = @Poller(fixedRate = "4000", maxMessagesPerPoll = "1"))
    public MessageSource<UserEntity> timemessageSender() {
        return () -> new GenericMessage<>(new UserEntity(1, "lea", "123456"));
    }

    /**
     *组的发送轮询
     */
    @Bean
    @InboundChannelAdapter(value = GroupChannel.GROUP_CHANNEL,poller = @Poller(fixedRate = "5000",maxMessagesPerPoll = "1"))
    public MessageSource groupChannel(){
        return () ->{
            count++;
            log.info("发送的值是{}",count);
           return  new GenericMessage<>(count);
        };
    }

}
