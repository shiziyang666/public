package com.kafak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @RequestMapping(value = "/message/send/{msg}",method = RequestMethod.GET)
    public String send(@PathVariable String msg){
        kafkaTemplate.send("demo","shus1", msg); //使用kafka模板发送信息
        return "success";
    }
}
