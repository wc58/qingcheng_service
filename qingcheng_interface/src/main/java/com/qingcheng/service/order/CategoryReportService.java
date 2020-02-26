package com.qingcheng.service.order;

import com.qingcheng.pojo.order.CategoryReport;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface CategoryReportService {

    public List<CategoryReport> category3Count(LocalDate date);

    public void createCategoryReport();

    public List<Map> category1Count(String startDate, String endDate);

}
