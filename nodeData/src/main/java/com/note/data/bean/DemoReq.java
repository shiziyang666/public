package com.note.data.bean;

import lombok.Data;

//Controller 接收参数
@Data
public class DemoReq {
    //触发时间 时间格式为 11:44:12
    public String triggerTime;
    //触发周期类型 1:每天,2:每周,3:每月,4:每年
    public Integer choice;
    //日期指定
    public String dayValue;

    public enum CHOICEType {
        day(1, "每天"),
        week(2, "每周"),
        month(3, "每月"),
        year(4, "每年"),
        ;
        private Integer code;
        private String msg;

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        CHOICEType(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }
}
