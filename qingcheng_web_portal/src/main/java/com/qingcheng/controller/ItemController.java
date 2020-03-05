package com.qingcheng.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingcheng.pojo.goods.Goods;
import com.qingcheng.pojo.goods.Sku;
import com.qingcheng.pojo.goods.Spu;
import com.qingcheng.service.goods.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.*;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Reference
    private SpuService spuService;

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
        //批量生成页面
        for (Sku sku : skus) {
            //创建上下文和数据模型
            Context context = new Context();
            HashMap<String, Object> map = new HashMap<>();
            map.put("spu", spu);
            map.put("sku", sku);
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
