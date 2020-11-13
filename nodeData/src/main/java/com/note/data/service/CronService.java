package com.note.data.service;

import com.note.data.bean.DemoReq;
import com.note.data.bean.TcycleTime;

public interface CronService {

    TcycleTime demoInsert(DemoReq demoReq);
}
