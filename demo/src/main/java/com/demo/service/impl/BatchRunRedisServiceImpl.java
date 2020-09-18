package com.demo.service.impl;

import com.demo.service.BatchRunRedisService;
import com.demo.util.BatchRunRedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BatchRunRedisServiceImpl implements BatchRunRedisService {

    @Autowired
    private RedisTemplate<String, Object> stringRedisTemplate;

    @Autowired
    private BatchRunRedisUtil batchRunRedisUtil;

    //批量添加redis  普通set消耗，管道set消耗，multiSet 的执行效率测试
    @Override
    public void BatchSet() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            stringRedisTemplate.opsForValue().set("aaa" + i, "a", 60);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("普通set消耗" + (endTime - startTime) + "毫秒");

        long startTime2 = System.currentTimeMillis();
        Map map = new HashMap(100000);
        for (int i = 0; i < 100000; i++) {
            map.put("bbb" + i, "b");
        }
        batchRunRedisUtil.batchSetOrExpire(map, 60l);
        long endTime2 = System.currentTimeMillis();
        System.out.println("管道set消耗" + (endTime2 - startTime2) + "毫秒");

        long startTime3 = System.currentTimeMillis();
        Map map2 = new HashMap(100000);
        for (int i = 0; i < 100000; i++) {
            map2.put("ccc" + i, "b");
        }
        batchRunRedisUtil.batchSet(map2);
        long endTime3 = System.currentTimeMillis();
        System.out.println("批量set消耗" + (endTime3 - startTime3) + "毫秒");
    }

}