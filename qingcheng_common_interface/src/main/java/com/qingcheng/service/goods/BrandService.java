package com.qingcheng.service.goods;

import com.github.pagehelper.Page;
import com.qingcheng.pojo.goods.Brand;

import java.util.List;
import java.util.Map;

public interface BrandService {

    /**
     * 查询全部
     *
     * @return
     */
    public List<Brand> findAll();

    /**
     * 分页查询
     *
     * @param page
     * @param size
     * @return
     */
    public Page<Brand> findPage(int page, int size);

    /**
     * 条件查询
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

    /**
     * id查询
     * @param id
     * @return
     */
    public Brand findById(Integer id);

    /**
     * 添加
     * @param brand
     */
    public void add(Brand brand);

    /**
     * 修改
     * @param brand
     */
    public void update(Brand brand);

    /**
     * id删除
     * @param id
     */
    public void delete(Integer id);

}
