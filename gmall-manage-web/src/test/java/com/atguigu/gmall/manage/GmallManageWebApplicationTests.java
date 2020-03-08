package com.atguigu.gmall.manage;


import org.apache.commons.lang3.StringUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallManageWebApplicationTests {

    //测试fastdfs
    @Test
    public void contextLoads() throws IOException, MyException {


        //选定配置文件
        String tracker = GmallManageWebApplicationTests.class.getResource("/tracker.conf").getPath();
        //获取配置文件
        ClientGlobal.init(tracker);

        //只要读到了全局配置文件,每个client就会带着tracker的ip地址
        TrackerClient trackerClient = new TrackerClient();

        //获取trackerServer实例
        TrackerServer trackerServer=trackerClient.getConnection();
        //通过tracker获取一个storage客户端
        StorageClient storageClient=new StorageClient(trackerServer,null);
        //通过storage就能上传下载文件了
        //有多个上传下载方法,常用的有两个
        //一种是拿到流了,按照byte上传,一种是知道路径,按照路径上传
        //下面是按照第二种上传
        String[] upload_files = storageClient.upload_file("C:\\Users\\Administrator\\Desktop\\v2-6e83b18a698f61027444655c1327b65c.jpg", "jpg", null);

        String url = "http://60.205.214.42";

        //打印一下返回值看看,发现是一段一段的访问url,于是把它拼接起来变成访问路径
        for (String upload_file : upload_files) {
//            System.out.println(upload_file);
            url+="/"+upload_file;
        }

        System.out.println(url);







    }

}
