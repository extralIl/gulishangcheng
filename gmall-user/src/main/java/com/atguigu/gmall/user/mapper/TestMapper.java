package com.atguigu.gmall.user.mapper;

import com.atguigu.gmall.user.bean.Test;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TestMapper {

//查询所有
    @Select("select * from test")
    List<Test> selectAllTest();

//查询一个
    @Select("select * from test where id = #{id}")
    List<Test> selectTestById(String id);

//模糊查询
    @Select("select * from test where username like CONCAT('%',#{username},'%')")
    List<Test> selectTestLikeName(String name);
//插入含有日期类型的
    @Insert("insert into test(username,password,birthday) value(#{userName},#{password},#{birthday})")
    void insertTest(Test test);

    @Delete("delete from test where id = #{id}")
    void deleteTest(String id);

    @Update("update test set userName=#{userName},password=#{password},birthday=#{birthday} where id=#{id}")
    void updateTest(Test test);
}
