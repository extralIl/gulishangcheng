package com.atguigu.gmall.user.controller;

import com.atguigu.gmall.user.bean.Test;
import com.atguigu.gmall.user.bean.UmsMember;
import com.atguigu.gmall.user.bean.UmsMemberReceiveAddress;
import com.atguigu.gmall.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserService userService;




    /**
     * 查询
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectUserById/{id}")//REST风格传参,浏览器输入/addUser/1
    public UmsMember selectUserById(@PathVariable String id){
        return userService.getUserById(id);
    }



    @RequestMapping("/getReceiveAddressByMemberId")
    @ResponseBody
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId( String memberId){////参数接受json格式
        List<UmsMemberReceiveAddress> umsMemberReceiveAddresses = userService.getReceiveAddressByMemberId(memberId);
        return umsMemberReceiveAddresses;
    }



    @ResponseBody
    @RequestMapping("/index")
    public String index(){
        return "hello user";
    }

    @ResponseBody
    @RequestMapping("/getAllUser")
    public List<UmsMember> getAllUser(){
        List<UmsMember> umsMembers = userService.getAllUser();

        return umsMembers;
    }


    //==========================================================================================================
    //以下是作业内容

    /**
     * 查询所有
     */
    @ResponseBody
    @RequestMapping("/selectAllTest")
    public List<Test> selectAllTest(){
        return userService.getAllTest();
    }

    /**
     * 查询一个
     */
    @ResponseBody
    @RequestMapping("/selectTestById")
    public List<Test> selectTestById(@RequestParam("id") String id){
        return userService.getTestById(id);
    }



    /**
     * 查询 like
     */
    @ResponseBody
    @RequestMapping("/selectTestLikeName")
    public List<Test> selectTestLikeName(@RequestBody Map<String ,String > map){
        String name = map.get("name");
        return userService.getTestLikeName(name);
    }



    /**
     * 增加
     * 跟java实体类绑定形式
     */
    @ResponseBody
    @RequestMapping("/addTest")
    public String addTest(@RequestBody Map<String ,String > map){
        Test test = new Test();
        test.setUserName(map.get("userName"));
        test.setPassword(map.get("password"));
//        转化时间,日期类型 2019-09-09,不能传入2019-9-9
        System.out.println(map.get("birthday")+"");
        LocalDate date = LocalDate.parse(map.get("birthday"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        test.setBirthday(date);
        userService.addTest(test);
        return "添加完毕";
    }



    /**
     * 删除
     *
     *
     */
    @ResponseBody
    @RequestMapping("/deleteTest")
    public String deleteTest(@RequestBody String id){
        userService.deleteTest(id);
        return "删除完毕";
    }

    /**
     * 修改
     */

    @ResponseBody
    @RequestMapping("/updateTestById")
    public String updateTestById(@RequestBody Map<String,String> map){
        Test test = new Test();
        test.setId(map.get("id"));
        test.setUserName(map.get("userName"));
        test.setPassword(map.get("password"));
        LocalDate date = LocalDate.parse(map.get("birthday"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        test.setBirthday(date);
        userService.updateTestById(test);
        return "更新完毕";
    }


























}
