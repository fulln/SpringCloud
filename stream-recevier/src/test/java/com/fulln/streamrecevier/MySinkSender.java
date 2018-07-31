package com.fulln.streamrecevier;

import com.fulln.streamrecevier.channel.ChannelReceiver;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MySinkSender {

    @Output(ChannelReceiver.CHANNEL)
    MessageChannel output();

}