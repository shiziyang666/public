package com.note.data.service.impl;

import com.note.data.bean.DemoReq;
import com.note.data.bean.TcycleTime;
import com.note.data.service.CronService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CronServiceImpl implements CronService {

    public TcycleTime demoInsert(DemoReq demoReq) {

        TcycleTime tcycleTime = new TcycleTime();

        if (StringUtils.isEmpty(demoReq.getTriggerTime())) {
            System.out.println("触发时间为空");
        }
        String[] split = demoReq.getTriggerTime().split(":");
        Integer index = 0;
        for (int i = 0; i < split.length; i++) {
            index++;
        }
        if (index >= 3) {
            tcycleTime.setHour(split[0]);
            tcycleTime.setMinute(split[1]);
            tcycleTime.setSecond(split[2]);
        } else {
            tcycleTime.setHour(split[0]);
            tcycleTime.setMinute(split[1]);
            tcycleTime.setSecond("0");
        }
        tcycleTime.setWeek(demoReq.dayValue);//每月几日
        tcycleTime.setMonth(demoReq.dayValue);//每周 几日
        //如果按每天，任务频控 需要关掉
        //触发周期  1:每天,2:每周,3:每月,4:每年
        String core = "";
        //Integer s = 1;//每天

        switch (demoReq.getChoice()) {
            case 1:
                //每天
                if (tcycleTime.getSecond().equals("0")) {
                    core += "*" + " " + tcycleTime.getMinute() + " " + tcycleTime.getHour() + " " + "* * *";
                } else {
                    core += tcycleTime.getSecond() + " " + tcycleTime.getMinute() + " " + tcycleTime.getHour() + " " + "? * *";
                }
                break;
            case 2:
                //每周
                if (!StringUtils.isEmpty(tcycleTime.getWeek())) {
                    core += "*" + " " + tcycleTime.getMinute() + " " + tcycleTime.getHour() + " " + tcycleTime.getWeek() + " " + "*" + " " + "*";
                }
                break;
            case 3:
                //每月
                if (!StringUtils.isEmpty(tcycleTime.getMonth())) {
                    core += "*" + " " + tcycleTime.getMinute() + " " + tcycleTime.getHour() + " " + tcycleTime.getWeek() + " " + "?" + " " + "*";
                }
                break;
        }
        tcycleTime.setCron(core);
        return tcycleTime;
        //2020-12-1 9:30:30
    }
}
