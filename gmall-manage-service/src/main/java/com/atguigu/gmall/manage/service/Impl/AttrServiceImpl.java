package com.atguigu.gmall.manage.service.Impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.bean.*;
import com.atguigu.gmall.manage.mapper.*;
import com.atguigu.gmall.service.AttrService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@Service
public class AttrServiceImpl implements AttrService {

    @Autowired
    AttrInfoMapper attrInfoMapper;

    @Autowired
    AttrValueMapper attrValueMapper;

    @Autowired
    PmsBaseSaleAttrMapper pmsBaseSaleAttrMapper;



    @Override
    public List<PmsBaseAttrInfo> getAttrByCatalog3Id(String catalog3Id) {
        PmsBaseAttrInfo pmsBaseAttrInfo = new PmsBaseAttrInfo();
        pmsBaseAttrInfo.setCatalog3Id(catalog3Id);
        //根据catalog3Id查找attrInfo
        List<PmsBaseAttrInfo> pmsBaseAttrInfos = attrInfoMapper.select(pmsBaseAttrInfo);
        for (PmsBaseAttrInfo baseAttrInfo : pmsBaseAttrInfos) {
            //根据attrInfo查找attrValues
            PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
            pmsBaseAttrValue.setAttrId(baseAttrInfo.getId());
            List<PmsBaseAttrValue> pmsBaseAttrValues = attrValueMapper.select(pmsBaseAttrValue);
            //把查询到的值集合装回Info
            baseAttrInfo.setAttrValueList(pmsBaseAttrValues);
        }
        return pmsBaseAttrInfos;
    }


    //宝藏语句,里面包含了增删改三种操作,以后忘了怎么用可以看这个
    // 如何用tk修改
    //保存,修改属性
    //这里我其实感觉应该用更新,无奈前端传来的格式是让替换

    /**
     * 如果是保存:前端传来一个没有id的attrinfo
     * 如果是更新:前端传来一个有id的attrinfo,
     * 识别为更新:要把对应infoId的attrvalue全部删除,然后把传来的attrvalue再次保存
     * @param pmsBaseAttrInfo
     * @return
     */
    @Override
    public String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {

        String id = pmsBaseAttrInfo.getId();
        if(StringUtils.isBlank(id)){
            // id为空，保存

            // 保存属性
            //设置主键返回策略就是让,插入后,这个对象的getId方法能获取到插入后的id,并留给下面方法使用
            attrInfoMapper.insertSelective(pmsBaseAttrInfo);//insert insertSelective 是否将null插入数据库

            // 保存属性值
            List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();
            for (PmsBaseAttrValue pmsBaseAttrValue : attrValueList) {
                pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
                attrValueMapper.insertSelective(pmsBaseAttrValue);
            }
        }else{
            // id不空，修改

            // 属性名修改                 (我觉得就是找一个不变项,然后把其他项替换掉)
            Example example = new Example(PmsBaseAttrInfo.class);
            example.createCriteria().andEqualTo("id",pmsBaseAttrInfo.getId());
            attrInfoMapper.updateByExampleSelective(pmsBaseAttrInfo,example);

            // 属性值修改
            // 按照属性id删除所有传过来的属性值
            PmsBaseAttrValue pmsBaseAttrValueDel = new PmsBaseAttrValue();
            pmsBaseAttrValueDel.setAttrId(pmsBaseAttrInfo.getId());
            attrValueMapper.delete(pmsBaseAttrValueDel);

            // 删除后，将新的属性值插入
            List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();
            for (PmsBaseAttrValue pmsBaseAttrValue : attrValueList) {
                attrValueMapper.insertSelective(pmsBaseAttrValue);
            }

        }


        return "success";
    }

    //根据attrId查找values
    @Override
    public List<PmsBaseAttrValue> getAttrValueListByAttrId(String attrId) {
        PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
        pmsBaseAttrValue.setAttrId(attrId);
        return attrValueMapper.select(pmsBaseAttrValue);
    }

    @Override
    public List<PmsBaseSaleAttr> baseSaleAttrList() {
        return pmsBaseSaleAttrMapper.selectAll();
    }




}
