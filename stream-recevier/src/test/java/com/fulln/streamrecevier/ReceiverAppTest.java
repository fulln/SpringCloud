package com.fulln.streamrecevier;

import com.fulln.streamrecevier.channel.ChannelReceiver;
import com.fulln.streamrecevier.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


/**
 * @program: SpringCloud
 * @description: 测试类
 * @author: fulln
 * @create: 2018-08-01 14:48
 * @Version： 0.0.1
 **/

@RunWith(SpringRunner.class)
@EnableBinding(value = {ReceiverAppTest.MyOutputSource.class})
public class ReceiverAppTest {


    @Resource(name = ChannelReceiver.OUTPUT1_CHANNEL)
    private MessageChannel send1;

    @Resource(name = ChannelReceiver.OUTPUT2_CHANNEL)
    private MessageChannel send2;

    @Resource(name = ChannelReceiver.CHANNEL)
    private MessageChannel Send3;

    @Test
    public void myOutputSourceTester() {

//        send1.send(MessageBuilder.withPayload("produce a message to " + ChannelReceiver.OUTPUT1_CHANNEL + " channel").build());
//
//        send2.send(MessageBuilder.withPayload("produce a message to " + ChannelReceiver.OUTPUT2_CHANNEL + " channel").build());

        UserEntity entity = new UserEntity();
        entity.setUserName("fulln");

        Send3.send(MessageBuilder.withPayload(entity)
                .setHeader("name", "lee")
                .build());
//        Send3.send(MessageBuilder.withPayload(JSON.toJSONString(entity)).build());
    }


    public interface MyOutputSource {

        @Output(ChannelReceiver.OUTPUT1_CHANNEL)
        MessageChannel output1();

        @Output(ChannelReceiver.OUTPUT2_CHANNEL)
        MessageChannel output2();

        @Output(ChannelReceiver.CHANNEL)
        MessageChannel output3();
    }

}
