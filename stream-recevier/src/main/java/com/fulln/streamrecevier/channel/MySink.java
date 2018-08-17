package com.fulln.streamrecevier.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MySink {
    /*********************************消费组示例通道******************************/
    String GROUP_CHANNEL = "group-channel";


    @Input(GROUP_CHANNEL)
    SubscribableChannel groupInput();
}
