package com.note.data.bean;

import lombok.Data;

import java.util.List;

@Data
public class TaskScheduleModel {

    /**
     * 所选作业类型:
     * 1  -> 每天
     * 2  -> 每月
     * 3  -> 每周
     */
    Integer jobType;

    /**一周的哪几天*/
    List<Integer> dayOfWeeks;

    /**一个月的哪几天*/
    List<Integer> dayOfMonths;

    /**秒  */
    Integer second;

    /**分  */
    Integer minute;

    /**时  */
    Integer hour;
}
