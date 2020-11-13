package com.camel.config;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.ExpressionNode;

import java.util.ArrayList;
import java.util.List;

public class DynamcRouteBuilder extends RouteBuilder {


    private String from;
    private String to;
    private String routeId;
    private String head;
    private String body;

    @Override
    public void configure() {

        ExpressionNode route = from(from).process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                System.out.println("123456:" + from);
                exchange.getIn().setHeader("foo", "bar");
            }
        }).filter(header("foo").isEqualTo("bar")).to(ExchangePattern.InOut, this.to);
        route.setId(routeId);
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        route.setBody(constant(list));
    }

    public DynamcRouteBuilder(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public DynamcRouteBuilder(String from, String to, String routeId, String head, String body) {
        this.from = from;
        this.to = to;
        this.routeId = routeId;
        this.head = head;
        this.body = body;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
