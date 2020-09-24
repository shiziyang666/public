package com.demo.filter.response;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by meiqiyuan on 2017/11/22.
 */
@Component
public class ResponseUtils {

    private final static Map EMPTY_OBJECT = new HashMap<>();

    public static class Body {
        private Integer code;
        private String msg;
        private Object data;

        public Body(Integer code, String msg, Object data) {
            this.code = code;
            this.msg = msg;
            this.data = data;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }


    public static Object success() {
        Body body = new Body(0, "", EMPTY_OBJECT);
        return body;
    }

    public static <T> Object success(int code, Object body) {
        Body body0 = new Body(code, "", body == null ? EMPTY_OBJECT : body);
        return body0;
    }

    public static <T> Object success(int code, String msg, Object body) {
        Body body0 = new Body(code, msg, body == null ? EMPTY_OBJECT : body);
        return body0;
    }

    public static Object success_body(Object data) {

        Body body = new Body(0, "", data == null ? EMPTY_OBJECT : data);
        return body;
    }


    public static Object failure400(String message) {
        Body body = new Body(1, message, EMPTY_OBJECT);
        return body;
    }


    public static Object failure2(String message) {
        Body body = new Body(2, message, EMPTY_OBJECT);
        return body;
    }


    public static Object failure_body(Object data) {
        Body body = new Body(1, "", data);
        return body;
    }

    public static Object failure(int code, String message) {
        Body body = new Body(code, message, EMPTY_OBJECT);
        return body;
    }

    public static Object failure(int code, String message, Object data) {
        Body body = new Body(code, message, data);
        return body;
    }


    public static Object failure(String message) {
        Body body = new Body(1, message, EMPTY_OBJECT);
        return body;
    }

    public static Object failure_data_list(String message) {
        Body body = new Body(1, message, new ArrayList<>());
        return body;
    }
}
