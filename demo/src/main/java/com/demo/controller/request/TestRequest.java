package com.demo.controller.request;

public class TestRequest {

    private String name;

    private Integer age;

    private Integer gender;

    private String aa;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAa() {
        return aa;
    }

    public void setAa(String aa) {
        this.aa = aa;
    }

    @Override
    public String toString() {
        return "TestRequest{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", aa='" + aa + '\'' +
                '}';
    }
}
