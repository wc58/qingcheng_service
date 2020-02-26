package com.qingcheng.service.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OrderTask {

    @Scheduled(cron = "3/2 * * * * ?")
    public void test() {
        System.out.println(new Date());
    }

}
