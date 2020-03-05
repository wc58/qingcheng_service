package com.qingcheng.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.qingcheng.pojo.goods.Goods;
import com.qingcheng.pojo.goods.Sku;
import com.qingcheng.pojo.goods.Spu;
import com.qingcheng.service.goods.CategoryService;
import com.qingcheng.service.goods.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.*;
import java.util.*;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Reference
    private SpuService spuService;

    @Reference
    private CategoryService categoryService;

    @Value("${pagePath}")
    private String pagePath;

    @Autowired
    private TemplateEngine templateEngine;

    @RequestMapping("/create")
    public void create(String spuId) {
        //获取商品信息
        Goods goods = spuService.findGoodsById(spuId);
        //获取sku列表
        List<Sku> skus = goods.getSkuList();
        //获取spu列表
        Spu spu = goods.getSpu();
        //取出分类字面值
        ArrayList<String> categorys = new ArrayList<>();
        //一级，二级，三级分类
        if (!StringUtils.isEmpty(spu.getCategory1Id()))
            categorys.add(categoryService.findById(spu.getCategory1Id()).getName());
        if (!StringUtils.isEmpty(spu.getCategory2Id()))
            categorys.add(categoryService.findById(spu.getCategory2Id()).getName());
        if (!StringUtils.isEmpty(spu.getCategory3Id()))
            categorys.add(categoryService.findById(spu.getCategory3Id()).getName());
        //批量生成页面
        for (Sku sku : skus) {
            //图片设置
            String[] split = sku.getImages().split(",");
            //创建上下文和数据模型
            Context context = new Context();
            HashMap<String, Object> map = new HashMap<>();
            map.put("spu", spu);
            map.put("sku", sku);
            map.put("categorys", categorys);
            //图片列表
            map.put("spuImages", spu.getImages().split(","));
            map.put("skuImages", sku.getImages().split(","));
            //参数和规格数据
            Map paraItems = JSON.parseObject(spu.getParaItems());//参数
            map.put("paraItems", paraItems);
            Map specItems = JSON.parseObject(sku.getSpec());//规格
            map.put("specItems", specItems);
            context.setVariables(map);
            //准备生成的目录
            File file = new File(pagePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            //生成的文件
            File dest = new File(file, sku.getId() + ".html");
            //生成页面
            PrintWriter printStream = null;
            try {
                printStream = new PrintWriter(dest, "UTF-8");
                templateEngine.process("item", context, printStream);
                System.out.println("生成页面：" + sku.getId() + ".html");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                printStream.close();
            }
        }
    }

}
