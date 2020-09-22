package com.demo.controller;

import com.demo.controller.request.TestRequest;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TestController {

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public void test(TestRequest request) {
        System.out.println(request.getName());
        System.out.println("TestController" + request);
    }
}
