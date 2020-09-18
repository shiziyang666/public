package com.demo.service;

public interface BatchRunRedisService {
    //批量添加redis  普通set消耗，管道set消耗，multiSet 的执行效率测试
    void BatchSet();
}
