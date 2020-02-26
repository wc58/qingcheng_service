package com.qingcheng.controller.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingcheng.pojo.order.CategoryReport;
import com.qingcheng.service.order.CategoryReportService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categoryReport")
public class CategoryReportController {

    @Reference
    private CategoryReportService categoryReportService;


    /**
     * 昨天数据统计
     * @return
     */
    @RequestMapping("/yesterday")
    public List<CategoryReport> yesterday() {
        //得到昨天日期
        LocalDate localDate = LocalDate.now().minusDays(1);
        return categoryReportService.category3Count(localDate);
    }

    /**
     * 统计一级分类数据
     * @param startDate
     * @param endDate
     * @return
     */
    @RequestMapping("/category1Count")
    public List<Map> category1Count(String startDate, String endDate) {
        return categoryReportService.category1Count(startDate, endDate);
    }

}
