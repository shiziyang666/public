package com.demo.controller;

import com.demo.controller.request.TestRequest;
import com.demo.filter.response.ResponseUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class TestController {

    //body形式加上@Valid @RequestBody 注解  ，form表单形式去掉
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public Object test(TestRequest request) {
        System.out.println("TestController" + request);
        return request;
    }
}
