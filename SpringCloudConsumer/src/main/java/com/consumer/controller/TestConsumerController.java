package com.consumer.controller;

import com.consumer.service.TestConsumerService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestConsumerController {

    @Autowired
    private TestConsumerService testConsumerService;

    @RequestMapping(name = "/aaa", method = RequestMethod.POST)
    public Object test() {
        String test = testConsumerService.testRequest();

        if (ObjectUtils.allNotNull(test)) {
            return test;
        }
        return "我是Consumer";
    }
}
