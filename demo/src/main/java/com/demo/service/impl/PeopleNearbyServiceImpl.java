package com.demo.service.impl;

import com.demo.service.PeopleNearbyService;
import com.demo.util.GeoHashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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
}
