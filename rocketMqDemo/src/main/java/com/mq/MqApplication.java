package com.mq;

import com.mq.publish.MySource;
import com.mq.service.TestService;
import com.mq.subscribe.MySink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding({MySource.class, MySink.class})
public class MqApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(MqApplication.class, args);
    }

    @Autowired
    private TestService testService;

    @Override
    public void run(String... args) throws Exception {
        testService.test();
    }
}
