package com.atguigu.gmall.service;

import com.atguigu.gmall.bean.PmsProductImage;
import com.atguigu.gmall.bean.PmsSkuInfo;

import java.util.List;

public interface SkuService {

    List<PmsProductImage> spuImageList(PmsSkuInfo pmsSkuInfo);
}
