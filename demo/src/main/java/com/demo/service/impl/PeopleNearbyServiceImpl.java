package com.demo.service.impl;

import com.demo.entity.MemberGpsEntity;
import com.demo.service.PeopleNearbyService;
import com.demo.util.GeoHashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * shiziyang
 */
@Service
public class PeopleNearbyServiceImpl implements PeopleNearbyService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private GeoHashUtil geoHashUtil;

    @Override
    public void postUserAddress(String key, double precision, double dimension, String name) {
        //对应redis原生命令:GEOADD user 116.48105 39.996794 zhangsan
        redisTemplate.opsForGeo().add(key, new Point(precision, dimension), name);
        geoHashUtil.redisGeoAdd(key, precision, dimension, name);
    }

    @Override
    public void listNearbyUser(String key, String name, Integer distance, Integer count) {
        GeoResults<RedisGeoCommands.GeoLocation<String>> geoResults = geoHashUtil.geoNearByPlace(key, name, distance, count);
        System.out.println(geoResults);
    }

    @Override
    public void listNearbyUserLimit(Integer pageIndex, Integer pageSize, String key, String name, String distance, String distanceUnit, String sort, String newKey) {
        //将附近的人存储到一个key里
        Object execute = execute("return redis.call('georadiusbymember',KEYS[1],KEYS[2],KEYS[3],KEYS[4],KEYS[5],'storedist',KEYS[6])", key, name, distance, distanceUnit, sort, newKey);
        //给新key设置失效时间
        redisTemplate.expire(newKey, 6, TimeUnit.HOURS);
        //删除自己
        redisTemplate.opsForGeo().remove(newKey, name);
        //开始条数
        Integer startPage = (pageIndex - 1) * pageSize;
        //结束条数
        Integer endPage = pageIndex * pageSize - 1;
        //获取分页信息
        Set<ZSetOperations.TypedTuple<Object>> aaa =
                redisTemplate.opsForZSet().rangeWithScores(newKey, startPage, endPage);
        //处理返回值
        List<MemberGpsEntity> memberGpsEntityList = aaa.stream().map(aa -> {
            MemberGpsEntity memberGpsEntity = new MemberGpsEntity();
            //名称
            memberGpsEntity.setName(aa.getValue().toString());
            //距离
            memberGpsEntity.setDistance(aa.getScore());
            return memberGpsEntity;
        }).collect(Collectors.toList());
        //打印结果
        System.out.println(memberGpsEntityList);
    }

    /**
     * 执行lua脚本
     *
     * @param text lua 脚本
     * @param str  lua脚本的参数
     */
    private Object execute(String text, String... str) {
        //参数处理
        List<String> params = Arrays.asList(str);
        DefaultRedisScript<Long> defaultRedisScript = new DefaultRedisScript<>();
        //设置返回值
        defaultRedisScript.setResultType(Long.class);
        //设置脚本
        defaultRedisScript.setScriptText(text);
        //执行命令
        Object execute = redisTemplate.execute(defaultRedisScript, params);
        return execute;
    }
}
