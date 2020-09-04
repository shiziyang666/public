package com.demo.service;

/**
 * shiziyang
 * 附近的人
 * <p>
 * 使用实例
 * <p>
 * 添加地理位置
 * peopleNearbyService.postUserAddress("city", 116.405285, 39.904989, "北京");
 * peopleNearbyService.postUserAddress("city", 121.47, 31.23, "上海");
 * peopleNearbyService.postUserAddress("city", 113.27, 23.13, "广州");
 * peopleNearbyService.postUserAddress("city", 43.86, 10.40, "深圳");
 * <p>
 * 获取附近的人地理位置
 * peopleNearbyService.listNearbyUser("city","深圳",8000,4);
 * <p>
 * 分页查询附近的人
 * peopleNearbyService.listNearbyUserLimit(1, 2, "city", "深圳", "8000", "km", "asc", "shenzhennewkey");
 */
public interface PeopleNearbyService {


    /***
     * 添加地理位置
     * @param key rediskey
     * @param precision 经度
     * @param dimension 维度
     * @param name 位置名称
     */
    void postUserAddress(String key, double precision, double dimension, String name);


    /***
     * 获取最近附近的人地理位置
     * @param key rediskey
     * @param name 位置名称
     * @param distance 范围km数
     * @param count 获取最近几条
     */
    void listNearbyUser(String key, String name, Integer distance, Integer count);


    /***
     * 分页获取最近附近的人地理位置
     * @param pageIndex 第几页
     * @param pageSize 每页条数
     * @param key redis key "city",
     * @param name 位置名称 "深圳",
     * @param distance 距离 "8000",
     * @param distanceUnit 距离单位 "km",
     * @param sort 排序 "asc",
     * @param newKey 新的redis key "shenzhennewkey"
     */
    void listNearbyUserLimit(Integer pageIndex, Integer pageSize, String key, String name, String distance, String distanceUnit, String sort, String newKey);
}
