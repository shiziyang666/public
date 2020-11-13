package com.mq.publish;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MySource {

    @Output("outPut1")
    MessageChannel outPut1();

    @Output("outPut2")
    MessageChannel outPut2();

    @Output("outPut3")
    MessageChannel outPut3();


}
