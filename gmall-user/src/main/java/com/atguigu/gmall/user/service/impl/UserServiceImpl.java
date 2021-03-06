package com.atguigu.gmall.user.service.impl;



import com.atguigu.gmall.bean.UmsMember;
import com.atguigu.gmall.bean.UmsMemberReceiveAddress;
import com.atguigu.gmall.service.UserService;
import com.atguigu.gmall.user.mapper.UmsMemberReceiveAddressMapper;
import com.atguigu.gmall.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired//这里是误报
     UserMapper userMapper;

    @Autowired
    UmsMemberReceiveAddressMapper umsMemberReceiveAddressMapper;



//    使用通用mapper来自动生成增删改查,不写sql

    /**
     * @param memberId
     * @return
     */
    @Override
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId) {
        UmsMemberReceiveAddress umsMemberReceiveAddress = new UmsMemberReceiveAddress();
        umsMemberReceiveAddress.setMemberId(memberId);
        List<UmsMemberReceiveAddress> umsMemberReceiveAddresses = umsMemberReceiveAddressMapper.select(umsMemberReceiveAddress);
        return umsMemberReceiveAddresses;
    }



//    @Override
//    public List<Test> getAllTest() {
//
//        return testMapper.selectAllTest();
//    }
//
//    @Override
//    public List<Test> getTestById(String id) {
//        return testMapper.selectTestById(id);
//    }
//
//    @Override
//    public List<Test> getTestLikeName(String name) {
//        return testMapper.selectTestLikeName(name);
//    }
//
//    @Override
//    public void addTest(Test test) {
//        testMapper.insertTest(test);
//    }
//
//    @Override
//    public void deleteTest(String id) {
//        testMapper.deleteTest(id);
//    }


//    @Override
//    public void updateTestById(Test test) {
//        testMapper.updateTest(test);
//    }



    @Override
    public List<UmsMember> getAllUser() {
        List<UmsMember> users = userMapper.selectAll();
        return users;
    }


    @Override
    public UmsMember getUserById(String id) {
        UmsMember umsMember = userMapper.selectByPrimaryKey(id);
        return umsMember;
    }


    @Override
    public void insertUser(UmsMember umsMember) {
        userMapper.insert(umsMember);
        System.out.println("插入成功");
    }

    @Override
    public void deleteUser(UmsMember umsMember) {
        userMapper.deleteByPrimaryKey(umsMember.getId());
    }

    @Override
    public void updateUser(UmsMember umsMember) {
        userMapper.updateByPrimaryKey(umsMember);
    }


}
