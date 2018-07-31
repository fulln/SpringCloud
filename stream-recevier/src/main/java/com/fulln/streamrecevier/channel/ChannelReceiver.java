package com.fulln.streamrecevier.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @program: SpringCloud
 * @description: 自定义Channel订阅通道
 * @author: fulln
 * @create: 2018-07-31 14:35
 * @Version： 0.0.1
 **/
public interface  ChannelReceiver {

    String CHANNEL = "thisInput";

    @Input(ChannelReceiver.CHANNEL)
    SubscribableChannel input();

}
