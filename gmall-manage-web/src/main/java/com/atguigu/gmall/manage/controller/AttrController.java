package com.atguigu.gmall.manage.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.*;
import com.atguigu.gmall.service.AttrService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import util.PmsUploadUtil;

import java.util.List;

@Controller
@CrossOrigin//不要忘记配置跨域
public class AttrController {


    @Reference
    AttrService attrService;

    //商家点击新增spu时,读取商家可以选择的销售属性名称    就是那个颜色,尺寸,版本,容量哪些选项卡
    @RequestMapping("baseSaleAttrList")
    @ResponseBody
    public List<PmsBaseSaleAttr> baseSaleAttrList(){
        List<PmsBaseSaleAttr> pmsBaseSaleAttrs = attrService.baseSaleAttrList();
        return pmsBaseSaleAttrs;
    }


    //在属性表页面,点击一个修改按钮,获取属性对应数据
    @RequestMapping("getAttrValueList")
    @ResponseBody
    public List<PmsBaseAttrValue> getAttrValueList(String attrId){
        List<PmsBaseAttrValue> attrValueList = attrService.getAttrValueListByAttrId(attrId);
        return attrValueList;
    }


    //在属性页面选完三级类目,获取属性表
    @RequestMapping("attrInfoList")
    @ResponseBody
    public List<PmsBaseAttrInfo> getAttrInfoList(String catalog3Id){
        List<PmsBaseAttrInfo> attrByCatalog3Id = attrService.getAttrByCatalog3Id(catalog3Id);
        return attrByCatalog3Id;
    }


    /**
     * 1 根据catalog3Id找
     * 2 获取attrname,以及多个attrvalue
     * @param pmsBaseAttrInfo
     * @return
     */
    //保存新增属性
    @RequestMapping("saveAttrInfo")
    @ResponseBody
    public String saveAttrInfo(@RequestBody PmsBaseAttrInfo pmsBaseAttrInfo){

        String success;
        try {
            success = attrService.saveAttrInfo(pmsBaseAttrInfo);
        }catch (Exception e){
            return "failed";
        }
        return success;
    }














}
