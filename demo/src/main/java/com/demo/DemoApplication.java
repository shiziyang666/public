package com.demo;

import com.demo.service.PeopleNearbyService;
import com.demo.service.ReadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    private ReadFileService readFileService;

    @Autowired
    private PeopleNearbyService peopleNearbyService;

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        //添加地理位置
//        peopleNearbyService.postUserAddress("city", 116.405285, 39.904989, "北京");
//        peopleNearbyService.postUserAddress("city", 121.47, 31.23, "上海");
//        peopleNearbyService.postUserAddress("city", 113.27, 23.13, "广州");
//        peopleNearbyService.postUserAddress("city", 43.86, 10.40, "深圳");

        //获取附近的人地理位置
        peopleNearbyService.listNearbyUser("city","深圳",8000,4);
    }

}
