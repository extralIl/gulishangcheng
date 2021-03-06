package com.atguigu.gmall.service;

import com.atguigu.gmall.bean.PmsBaseAttrInfo;
import com.atguigu.gmall.bean.PmsBaseAttrValue;
import com.atguigu.gmall.bean.PmsBaseSaleAttr;
import com.atguigu.gmall.bean.PmsProductInfo;

import java.util.List;


public interface AttrService {

    List<PmsBaseAttrInfo> getAttrByCatalog3Id(String catalog3Id);

    String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

    List<PmsBaseAttrValue> getAttrValueListByAttrId(String attrId);

    List<PmsBaseSaleAttr> baseSaleAttrList();


}
