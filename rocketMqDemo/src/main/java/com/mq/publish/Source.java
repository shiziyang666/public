package com.mq.publish;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Source {

    String output = "output";

    @Output(output)
    MessageChannel output();
}
