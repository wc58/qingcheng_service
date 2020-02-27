package com.qingcheng.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qingcheng.dao.CategoryReportMapper;
import com.qingcheng.pojo.order.CategoryReport;
import com.qingcheng.service.order.CategoryReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = CategoryReportService.class)
public class CategoryReportServiceImpl implements CategoryReportService {

    @Autowired
    private CategoryReportMapper categoryReportMapper;

    /**
     * 统计商品数据
     *
     * @param date
     * @return
     */
    @Override
    public List<CategoryReport> category3Count(LocalDate date) {
        return categoryReportMapper.categoryReport(date);
    }

    /**
     * 保存统计商品数据
     */
    @Override
    @Transactional
    public void createCategoryReport() {
        LocalDate localDate = LocalDate.now().minusDays(1);
        List<CategoryReport> categoryReports = this.category3Count(localDate);
        for (CategoryReport categoryReport : categoryReports) {
            CategoryReport select = categoryReportMapper.selectByPrimaryKey(categoryReport);
            if (select == null)
                categoryReportMapper.insert(categoryReport);
            categoryReportMapper.updateByPrimaryKeySelective(categoryReport);
        }
    }

    @Override
    public List<Map<String,String>> category1Count(String startDate, String endDate) {
        return categoryReportMapper.category1Count(startDate, endDate);
    }


}
