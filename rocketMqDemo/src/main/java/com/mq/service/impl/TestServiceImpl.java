package com.mq.service.impl;

import com.mq.publish.MySource;
import com.mq.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private MySource mySource;

    @Override
    public void test() {
        MessageBuilder<byte[]> messageBuilder = MessageBuilder.withPayload("测试消息".getBytes());
        mySource.outPut1().send(messageBuilder.build());
    }


}
