package com.qingcheng.controller.goods;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingcheng.entity.PageResult;
import com.qingcheng.entity.R;
import com.qingcheng.entity.Result;
import com.qingcheng.pojo.goods.Goods;
import com.qingcheng.pojo.goods.Spu;
import com.qingcheng.service.goods.SpuService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/spu")
public class SpuController {

    @Reference
    private SpuService spuService;

    @PostMapping("/save")
    public R save(@RequestBody Goods goods) {
        try {
            spuService.saveGoods(goods);
        } catch (Exception e) {
            e.printStackTrace();
            return R.ERROR();
        }
        return R.OK();
    }

    @GetMapping("/findGoodsById")
    public Goods findGoodsById(String id) {
        return spuService.findGoodsById(id);
    }

    @PostMapping("/audit")
    public R audit(@RequestBody Map<String, String> map) {
        try {
            spuService.audit(map.get("id"), map.get("status"), map.get("message"));
        } catch (Exception e) {
            e.printStackTrace();
            return R.ERROR();
        }
        return R.OK();
    }

    @GetMapping("/pull")
    public R pull(String id) {
        try {
            spuService.pull(id);
        } catch (Exception e) {
            e.printStackTrace();
            return R.ERROR();
        }
        return R.OK();
    }

    @GetMapping("/push")
    public R push(String id) {
        try {
            spuService.push(id);
        } catch (Exception e) {
            e.printStackTrace();
            return R.ERROR();
        }
        return R.OK();
    }

    @GetMapping("/putMany")
    public R putMany(String[] ids) {
        try {
            spuService.putMany(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return R.ERROR();
        }
        return R.OK();
    }

    @GetMapping("/findAll")
    public List<Spu> findAll() {
        return spuService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Spu> findPage(int page, int size) {
        return spuService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Spu> findList(@RequestBody Map<String, Object> searchMap) {
        return spuService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Spu> findPage(@RequestBody Map<String, Object> searchMap, int page, int size) {
        return spuService.findPage(searchMap, page, size);
    }

    @GetMapping("/findById")
    public Spu findById(String id) {
        return spuService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody Spu spu) {
        spuService.add(spu);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Spu spu) {
        spuService.update(spu);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(String id) {
        spuService.delete(id);
        return new Result();
    }

}
