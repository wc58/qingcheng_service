package com.qingcheng.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qingcheng.dao.BrandMapper;
import com.qingcheng.pojo.goods.Brand;
import com.qingcheng.service.goods.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 品牌功能的实现
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> findAll() {
        //直接调用接口查询全部
        return brandMapper.selectAll();
    }

    @Override
    public Map<String,Object> findPage(int page, int size) {
        //使用分页插件拦截sql达到分页效果
        PageHelper.startPage(page, size);
        //强制转换为分页结果集
        Page<Brand> brandPage = (Page<Brand>) brandMapper.selectAll();
        //封装数据
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",brandPage.getTotal());
        map.put("rows",brandPage.getResult());
        return map;
    }

    @Override
    public List<Brand> searchList(Map<String, Object> searchMap) {
        //设置条件
        Example example = createExample(searchMap);
        List<Brand> brandList = brandMapper.selectByExample(example);
        return brandList;
    }


    @Override
    public Map<String,Object> findPage(Map<String, Object> searchMap, int page, int size) {
        //设置条件
        Example example = createExample(searchMap);
        //使用分页插件拦截sql达到分页效果
        PageHelper.startPage(page, size);
        Page<Brand> brandPage = (Page<Brand>) brandMapper.selectByExample(example);
        //封装数据
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",brandPage.getTotal());
        map.put("rows",brandPage.getResult());
        return map;
    }

    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Brand brand) {
        brandMapper.insert(brand);
    }

    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public void delete(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    /**
     * 封装查询条件
     *
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        //防止空指针
        if (searchMap != null) {
            String name = (String) searchMap.get("name");
            String letter = (String) searchMap.get("letter");
            //姓名模糊查询
            if (!StringUtils.isEmpty(name)) {
                criteria.andLike("name", "%" + name + "%");
            }
            //首字母准确查询
            if (!StringUtils.isEmpty(letter)) {
                criteria.andEqualTo("letter", letter);
            }
        }
        return example;
    }

}
