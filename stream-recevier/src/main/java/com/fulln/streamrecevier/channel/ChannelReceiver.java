package com.fulln.streamrecevier.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @program: SpringCloud
 * @description: 自定义Channel订阅通道
 * @author: fulln
 * @create: 2018-07-31 14:35
 * @Version： 0.0.1
 **/
public interface  ChannelReceiver {

    String OUTPUT1_CHANNEL="OutPut-1";
    String OUTPUT2_CHANNEL="OutPut-2";

    String REPLAY_SINK_CHANNEL = "replay-sink-channel";
    String REPLAY_SOURCE_CHANNEL = "replay-source-channel";

    @Input(REPLAY_SOURCE_CHANNEL)
    SubscribableChannel replayInput();

    @Output(REPLAY_SINK_CHANNEL)
    MessageChannel replayOutput();


    /**
     * 原生通道
     */
    String ORIGINAL_CHANNEL = "original-channel";

    String CHANNEL = "thisInput";

    @Input(CHANNEL)
    SubscribableChannel input();

    @Input(OUTPUT1_CHANNEL)
    SubscribableChannel  input1();

    @Input(OUTPUT2_CHANNEL)
    SubscribableChannel input2();

}
