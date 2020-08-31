package com.demo.entity;

public class MemberGpsEntity {
    /**
     * 名称
     */
    private String name;

    /**
     * 距离
     */
    private Double distance;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "MemberGpsEntity{" +
                "name='" + name + '\'' +
                ", distance=" + distance +
                '}';
    }
}
