package com.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "SpringCloudProducer", fallbackFactory = TestConsumerServiceImpl.class)
public interface TestConsumerService {

    @RequestMapping(name = "/testRequest", method = RequestMethod.POST)
    public String testRequest();

}
