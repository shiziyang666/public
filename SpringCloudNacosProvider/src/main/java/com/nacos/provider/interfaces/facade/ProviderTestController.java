package com.nacos.provider.interfaces.facade;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProviderTestController {

    //test
    @RequestMapping(value = "/helloNacos", method = RequestMethod.GET)
    public String dataListLabel() {
        log.info("[LabelController]-[dataListLabel]-[/data/list/label:begin]-[request]");
        return "hello nacos";
    }

}
