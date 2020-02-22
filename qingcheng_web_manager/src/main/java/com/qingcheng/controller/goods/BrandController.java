package com.qingcheng.controller.goods;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.Page;
import com.qingcheng.pojo.entity.R;
import com.qingcheng.pojo.goods.Brand;
import com.qingcheng.service.goods.BrandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping("/findAll")
    public List<Brand> findAll() {
        return brandService.findAll();
    }

    /**
     * 分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findPage/{page}/{size}")
    public R findPage(@PathVariable(value = "page") Integer page, @PathVariable(value = "size") Integer size) {
        Map<String, Object> pageResult = brandService.findPage(page, size);
        return R.OK().data(pageResult);
    }

    /**
     * 条件查询
     *
     * @param searchMap
     * @return
     */
    @PostMapping("/searchList")
    public R searchList(@RequestBody Map<String, Object> searchMap) {
        List<Brand> brands = brandService.searchList(searchMap);
        return R.OK().data("items", brands);
    }

    /**
     * 分页条件查询
     *
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/findPage/{page}/{size}")
    public R searchPage(@RequestBody Map<String, Object> searchMap, @PathVariable(value = "page") Integer page, @PathVariable(value = "size") Integer size) {
        Map<String, Object> pageResult = brandService.findPage(searchMap, page, size);
        return R.OK().data(pageResult);
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    public R findById(@PathVariable(value = "id") Integer id) {
        return R.OK().data("item", brandService.findById(id));
    }

    /**
     * 添加
     *
     * @param brand
     * @return
     */
    @PostMapping("/add")
    public R add(@RequestBody Brand brand) {
        brandService.add(brand);
        return R.OK();
    }

    /**
     * 修改
     *
     * @param brand
     * @return
     */
    @PostMapping("/update")
    public R update(@RequestBody Brand brand) {
        brandService.update(brand);
        return R.OK();
    }

    /**
     * id删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable(value = "id") Integer id) {
        brandService.delete(id);
        return R.OK();
    }

}
