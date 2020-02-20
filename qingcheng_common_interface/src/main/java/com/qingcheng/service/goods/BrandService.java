package com.qingcheng.service.goods;

import com.github.pagehelper.Page;
import com.qingcheng.pojo.goods.Brand;

import java.util.List;

public interface BrandService {

    /**
     * 查询全部品牌
     * @return
     */
    public List<Brand> findAll();

    /**
     * 分页查询品牌
     * @param page 当前页码
     * @param size 总条数
     * @return
     */
    public Page<Brand> findPage(int page, int size);

}
