package com.qingcheng.service.impl;

import com.qingcheng.pojo.order.CategoryReport;
import com.qingcheng.service.order.CategoryReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OrderTask {

    @Autowired
    private CategoryReportService categoryReportService;

    /**
     * 定时生成统计数据
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void test() {
        System.out.println("统计数据");
        categoryReportService.createCategoryReport();
    }

}
