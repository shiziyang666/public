package com.demo.filter.response;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 施自扬
 * 返回结果处理类
 */
@ControllerAdvice
public class ResponseAdvisor implements ResponseBodyAdvice<Object> {

    /**
     * 判断支持的类型 施自扬
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        //注意，这里必须返回true，否则不会执行beforeBodyWrite方法
        return true;
    }

    /**
     * 过滤  施自扬
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        //获取请求的url
        String requestPath = request.getURI().getPath();
        //开始为“/test”的url进行过滤
        if (!requestPath.startsWith("/test")) {
            return body;
        }
        //如果是返回客户端模板类不进行过滤
        if (body instanceof ResponseUtils.Body) {
            return body;
        }
        return ResponseUtils.success_body(body);
    }
}
