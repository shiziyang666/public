package com.demo.filter.request;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@WebFilter
public class EncryptionFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    private final static String JSON_TO_PARAM = "JSONS";

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        String contentType = request.getContentType();
        if (contentType.equals(MediaType.APPLICATION_JSON_UTF8_VALUE)
                || contentType.equals(MediaType.APPLICATION_JSON_VALUE)) {
            //获取request的body参数
            String postContent = getBody(request);
            //如果body中存在数据放入HttpServletRequest
            if (StringUtils.isNotEmpty(postContent)) {
                //将参数放入重写的方法中
                request = new BodyRequestWrapper(request, postContent);
            }
        } else if ((contentType.equals(MediaType.APPLICATION_FORM_URLENCODED_VALUE) || contentType.contains(MediaType.MULTIPART_FORM_DATA_VALUE))
                && !request.getParameterMap().isEmpty()) {
//            String body = "{\"name\":\"zhangsan\",\"age\":1,\"gender\":2}";
            //重新添加几个数据
            Map<String, String[]> parameterMap = new HashMap<>();
            parameterMap.put("name", new String[]{"zhangsan"});
            parameterMap.put("age", new String[]{"1"});
            parameterMap.put("gender", new String[]{"2"});
            //将参数放入重写的方法中
            request = new ParameterRequestWrapper(request, parameterMap);
        }
        chain.doFilter(request, response);
    }

    //获取Request的body数据
    private String getBody(ServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        InputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {

        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public void destroy() {

    }
}
