package com.atguigu.gmall.user.service;

import com.atguigu.gmall.user.bean.Test;
import com.atguigu.gmall.user.bean.UmsMember;
import com.atguigu.gmall.user.bean.UmsMemberReceiveAddress;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserService  {
    List<UmsMember> getAllUser();
    List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId);

    UmsMember getUserById(String id);

    List<Test> getAllTest();

    List<Test> getTestById(String id);

    List<Test> getTestLikeName(String name);

    void addTest(Test test);

    void deleteTest(String id);

    void updateTestById(Test test);
}
