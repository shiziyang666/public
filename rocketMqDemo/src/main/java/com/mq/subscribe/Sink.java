package com.mq.subscribe;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Sink {

    String input = "input";

    @Input(input)
    SubscribableChannel input();
}
