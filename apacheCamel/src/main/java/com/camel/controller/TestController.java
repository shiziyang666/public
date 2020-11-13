package com.camel.controller;

import com.camel.config.DynamcRouteBuilder;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    CamelContext camelContext;

    @Autowired
    ProducerTemplate producerTemplate;

    @GetMapping("createRoute")
    public String createRoute() throws Exception {
//        ProducerTemplate template = camelContext.createProducerTemplate();
//        camelContext.start();
        producerTemplate.sendBodyAndHeader("direct:beginNode", "12345", "foo", "bar");
//        camelContext.stop();
        return "success";
    }

    //发布路由
    @GetMapping("route")
    public String route() {

        try {
            DynamcRouteBuilder dynamcRouteBuilder1 = new DynamcRouteBuilder("direct:beginNode", "direct:ABNode", "routeId1", "", "");
            DynamcRouteBuilder dynamcRouteBuilder2 = new DynamcRouteBuilder("direct:ABNode", "log:3success!!!!", "routeId2", "", "");
            camelContext.addRoutes(dynamcRouteBuilder1);
            camelContext.addRoutes(dynamcRouteBuilder2);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "success";
    }
}
