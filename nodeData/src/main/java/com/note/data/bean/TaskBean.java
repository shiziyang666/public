package com.note.data.bean;

import lombok.Data;

import java.util.List;

@Data
public class TaskBean {

    //任务id
    private String jobId;
    //任务名称
    private String jobName;
    //任务类型
    private String jobType;

    private String jobDescription;

    private String jobVersion;

    private String triggerWay;

    private String startTime;

    private String endTime;

    private String jobState;

    private String createBy;

    private String approvalTime;

    //控件集合
    private List<NodeBean> nodeBeanList;

    //连线集合
    private List<NodeLineBean> nodeLineBeanList;
}
