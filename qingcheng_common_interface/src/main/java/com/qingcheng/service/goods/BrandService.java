package com.qingcheng.service.goods;

import com.github.pagehelper.Page;
import com.qingcheng.pojo.goods.Brand;

import java.util.List;
import java.util.Map;

public interface BrandService {

    /**
     * 查询全部品牌
     *
     * @return 品牌集合
     */
    public List<Brand> findAll();

    /**
     * 分页查询品牌
     *
     * @param page 当前页码
     * @param size 总条数
     * @return 分页结果（品牌）
     */
    public Page<Brand> findPage(int page, int size);

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    public List<Brand> searchList(Map<String, Object> searchMap);

    /**
     * 分页条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public Page<Brand> findPage(Map<String, Object> searchMap, int page, int size);

}
