package com.note.data.bean;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class NodeBean {

    //控件id
    private String id;
    //控件名称
    private String caption;
    //控件类型
    private String nodeTypeID;
    //控件描述
    private String info;
    //上级端点编码
    private String pinName;
    //下级端点编码集合
    private List<String> pinNameList;
    //控件详细信息
    private Map<String, Object> params;
    //控件详细信息
    private Map<String, Object> nodeInfo;

}
