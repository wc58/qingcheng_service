package com.qingcheng.service.goods;

import com.qingcheng.pojo.goods.Brand;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import java.util.List;

public interface BrandService {

    public List<Brand> findAll();

}
