package com.camel;

import com.camel.config.DynamcRouteBuilder;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CamelApplication {

    public static void main(String[] args) {
        SpringApplication.run(CamelApplication.class, args);
    }

//    @Override
//    public void run(String... args) {
//        try {
//            test();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private void test() throws Exception {
        CamelContext myCamelContext = new DefaultCamelContext();
//        RouteBuilder builder = new RouteBuilder() {
//            public void configure() {
//                String a = "1";
//                String b = "2";
//                String c = "3";
//                String d = "4";
////                fromF("direct:%s", a).to("log:1success!!!")
////                        .filter(header("foo").isEqualTo("bar"))
////                        .toF("direct:%s", b);
////                fromF("direct:%s",b)
////                        .toF("log:2success!!!!%s", "123456");
//                ChoiceDefinition choiceDefinition = fromF("direct:%s", a).choice()
//                        .when(header("foo").isEqualTo("bar"))
//                        .toF("log:%s", "bbbbbSuccess")
//                        .when(header("foo").isEqualTo("cheese"))
//                        .toF("log:%s", "cccccSuccess")
//                        .otherwise()
//                        .toF("log:%s", "ddddddSuccess");
//            }
//        };
        DynamcRouteBuilder dynamcRouteBuilder = new DynamcRouteBuilder("direct:1","log:2success!!!!");
        myCamelContext.addRoutes(dynamcRouteBuilder);
        ProducerTemplate template = myCamelContext.createProducerTemplate();
        myCamelContext.start();
        template.sendBodyAndHeader("direct:1", "12345", "foo", "bar");
    }
}
