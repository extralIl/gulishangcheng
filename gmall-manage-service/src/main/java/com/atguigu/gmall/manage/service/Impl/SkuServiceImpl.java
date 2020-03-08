package com.atguigu.gmall.manage.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.bean.PmsProductImage;
import com.atguigu.gmall.bean.PmsSkuInfo;
import com.atguigu.gmall.manage.mapper.PmsSkuInfoMapper;
import com.atguigu.gmall.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    PmsSkuInfoMapper pmsSkuInfoMapper;

    @Override
    public List<PmsProductImage> spuImageList(PmsSkuInfo pmsSkuInfo) {
        //插入skuInfo

        //插入平台属性关联

        //插入销售属性关联

        //插入图片信息

        return null;
    }
}
