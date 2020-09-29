package com.producer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestProducerController {

    @RequestMapping(name = "/testRequest",method = RequestMethod.POST)
    public String testRequest(){
        return "我是Producer";
    }
}
