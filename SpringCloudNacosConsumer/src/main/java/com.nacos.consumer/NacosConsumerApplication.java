package com.nacos.consumer;

import com.nacos.provider.api.client.ProviderTestApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//开启服务注册发现功能
@EnableDiscoveryClient
//开启feign调用功能
@EnableFeignClients(basePackages = {"com.nacos.provider.api"})
public class NacosConsumerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerApplication.class);
    }

    //httpclient（根feign无关）
    @Autowired
    private RestTemplate restTemplate;

    //httpclient（根feign无关）
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    //feign调用生产者api
    @Autowired
    private ProviderTestApi providerTestApi;

    @Override
    public void run(String... args) throws Exception {
        //httpclient调用
//        String forObject = restTemplate.getForObject("http://nacos-provide/helloNacos", String.class);
//        System.out.println(forObject);

        //feign调用
        String forObject = providerTestApi.dataListLabel();
        System.out.println(forObject);
    }
}
