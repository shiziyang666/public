package com.demo.filter.request;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

//更改body中的值
public class BodyRequestWrapper extends HttpServletRequestWrapper {


    // 存放JSON数据主体
    private String body;

    protected Map<String, String[]> parameters = null;

    public BodyRequestWrapper(HttpServletRequest request, String context) {
        super(request);
        JSONObject jsStr = JSONObject.parseObject(context);
        jsStr.put("aa", "bb");
        body = jsStr.toJSONString();
    }

    public BodyRequestWrapper(HttpServletRequest request, Map map) {
        super(request);
        parameters = map;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> parameterMap = parameters;
        return parameterMap;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes("UTF-8"));
        ServletInputStream servletInputStream = new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }
        };
        return servletInputStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public String getBody() {
        return this.body;
    }
}
