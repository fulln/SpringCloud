package com.fulln.streamrecevier;

import com.fulln.streamrecevier.channel.ChannelReceiver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;


import org.springframework.context.MessageSource;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;


import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@EnableBinding(value = {ReceiverAppTest.SinkSender.class,MySinkSender.class})
public class ReceiverAppTest {

    @Autowired
    private SinkSender sinkSender;

    @Resource
    private MySinkSender mySinkSender;


    @Test
    public void sinkSenderTester() {
//        sinkSender.output().send(MessageBuilder.withPayload("produce a message to  channel").build());
        Message message = MessageBuilder.withPayload("produce a message to " + ChannelReceiver.CHANNEL + " channel").build();
        System.err.println(message);
        mySinkSender.output().send(message);
    }


    public interface SinkSender {

        @Output(Sink.INPUT)
        MessageChannel output();

    }


}
