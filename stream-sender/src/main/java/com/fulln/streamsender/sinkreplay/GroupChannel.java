package com.fulln.streamsender.sinkreplay;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

/**
 * @program: SpringCloud
 * @description: 组channel 的接收
 * @author: fulln
 * @create: 2018-08-06 16:46
 * @Version： 0.0.1
 **/
public interface GroupChannel {

    String GROUP_CHANNEL = "group-channel";

    @Output(value = GROUP_CHANNEL)
    SubscribableChannel groupInput();

}
