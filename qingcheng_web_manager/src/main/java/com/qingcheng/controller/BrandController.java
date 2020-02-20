package com.qingcheng.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.Page;
import com.qingcheng.pojo.entity.PageResult;
import com.qingcheng.pojo.entity.R;
import com.qingcheng.pojo.goods.Brand;
import com.qingcheng.service.goods.BrandService;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    /**
     * 查询所有品牌
     * @return
     */
    @GetMapping("/findAll")
    public List<Brand> findAll() {
        return brandService.findAll();
    }

    /**
     * 分页查询品牌
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findPage/{page}/{size}")
    public R findPage(@PathVariable(value = "page") Integer page, @PathVariable(value = "size") Integer size){
        Page<Brand> pageResult = brandService.findPage(page, size);
        return R.OK().data("total",pageResult.getTotal()).data("rows",pageResult.getResult());
    }

}
