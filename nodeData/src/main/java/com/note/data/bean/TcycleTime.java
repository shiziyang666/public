package com.note.data.bean;

import lombok.Data;

@Data
public class TcycleTime {

    private Integer id;
    private String conditionId;
    private String year;
    private String month;
    private String week;
    private String day;
    private String hour;
    private String minute;
    private String second;
    private String cron;
    private String timeVersion;

    public TcycleTime() {
    }

    @Override
    public String toString() {
        return "TcycleTime{" +
                "id=" + id +
                ", conditionId='" + conditionId + '\'' +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", week='" + week + '\'' +
                ", day='" + day + '\'' +
                ", hour='" + hour + '\'' +
                ", minute='" + minute + '\'' +
                ", second='" + second + '\'' +
                ", cron='" + cron + '\'' +
                ", timeVersion='" + timeVersion + '\'' +
                '}';
    }

    public TcycleTime(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConditionId() {
        return conditionId;
    }

    public void setConditionId(String conditionId) {
        this.conditionId = conditionId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getTimeVersion() {
        return timeVersion;
    }

    public void setTimeVersion(String timeVersion) {
        this.timeVersion = timeVersion;
    }
}
