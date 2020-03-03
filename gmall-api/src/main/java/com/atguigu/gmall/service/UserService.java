package com.atguigu.gmall.service;


import com.atguigu.gmall.bean.UmsMember;
import com.atguigu.gmall.bean.UmsMemberReceiveAddress;

import java.util.List;

public interface UserService {
    List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId);


//    List<Test> getAllTest();
//
//    List<Test> getTestById(String id);
//
//    List<Test> getTestLikeName(String name);
//
//    void addTest(Test test);
//
//    void deleteTest(String id);
//
//    void updateTestById(Test test);


    List<UmsMember> getAllUser();

    UmsMember getUserById(String id);

    void insertUser(UmsMember umsMember);

    void deleteUser(UmsMember umsMember);

    void updateUser(UmsMember umsMember);
}
