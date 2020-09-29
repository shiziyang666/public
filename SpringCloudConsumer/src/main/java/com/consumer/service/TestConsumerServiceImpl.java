package com.consumer.service;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class TestConsumerServiceImpl implements FallbackFactory<TestConsumerService> {

    @Override
    public TestConsumerService create(Throwable cause) {
        return new TestConsumerService() {
            @Override
            public String testRequest() {
                return null;
            }
        };
    }
}
