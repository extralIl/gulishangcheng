package com.atguigu.gmall.user.mapper;

import com.atguigu.gmall.user.bean.UmsMember;
import org.springframework.context.annotation.Bean;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface UserMapper extends Mapper<UmsMember> {
    //    使用tk mapper来使用通用maper
    List<UmsMember> selectAllUser();
}
