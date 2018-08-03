package com.fulln.streamsender.sinkreplay;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @program: SpringCloud
 * @description: 消息接受回复channel
 * @author: fulln
 * @create: 2018-08-02 15:39
 * @Version： 0.0.1
 **/
public interface RecordingChannel {

    String REPLAY_SINK_CHANNEL = "replay-sink-channel";

    String REPLAY_SOURCE_CHANNEL = "replay-source-channel";


    @Input(REPLAY_SINK_CHANNEL)
    SubscribableChannel replayInput();

    @Output(REPLAY_SOURCE_CHANNEL)
    MessageChannel replayOutput();


}
