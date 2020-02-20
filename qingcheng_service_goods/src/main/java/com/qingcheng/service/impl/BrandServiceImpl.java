package com.qingcheng.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qingcheng.dao.BrandMapper;
import com.qingcheng.pojo.goods.Brand;
import com.qingcheng.service.goods.BrandService;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

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
        return brandMapper.selectAll();
    }

    @Override
    public Page<Brand> findPage(int page, int size) {
        //使用分页插件拦截sql达到分页效果
        PageHelper.startPage(page, size);
        //强制转换为分页结果集
        Page<Brand> brandPage = (Page<Brand>) brandMapper.selectAll();
        return brandPage;
    }

    @Override
    public List<Brand> searchList(Map<String, Object> searchMap) {
        //设置条件
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
        //执行条件查询
        List<Brand> brandList = brandMapper.selectByExample(example);
        return brandList;
    }
}
