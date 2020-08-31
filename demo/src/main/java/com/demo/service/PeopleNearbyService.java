package com.demo.service;

public interface PeopleNearbyService {
    void postUserAddress(String key,double precision, double dimension, String name);

    void listNearbyUser(String key, String name, Integer distance, Integer count);
}
