package util;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class PmsUploadUtil {

    public static String uploadImage(MultipartFile multipartFile){
        //这个我觉得应该从配置文件中获取
        String imgUrl="http://60.205.214.42";

        //上传图片的服务器代码
        //选定配置文件
        String tracker = PmsUploadUtil.class.getResource("/tracker.conf").getPath();
        //获取配置文件
        try {
            ClientGlobal.init(tracker);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            System.out.println("my exceptionnnn! 上传文件代码中获取配置文件出错");
            e.printStackTrace();
        }

        //只要读到了全局配置文件,每个client就会带着tracker的ip地址
        TrackerClient trackerClient = new TrackerClient();

        //获取trackerServer实例
        TrackerServer trackerServer= null;
        try {
            trackerServer = trackerClient.getConnection();
            StorageClient storageClient=new StorageClient(trackerServer,null);
            byte[] bytes = multipartFile.getBytes();
            //取最后一个点后的字符串作为后缀名
            String originalFilename = multipartFile.getOriginalFilename();
            int i = originalFilename.lastIndexOf(".");
            String extName = originalFilename.substring(i+1);

            String[] upload_files = storageClient.upload_file(bytes,extName,null);
            for (String upload_file : upload_files) {
                imgUrl+="/"+upload_file;
            }

        } catch (IOException e) {
            System.out.println("my exception!!!!!!  上传文件代码中getConnection出错");
            e.printStackTrace();
        } catch (MyException e) {
            System.out.println("my exception!!!!!!  上传文件代码中upload_file出错");
            e.printStackTrace();
        }

        return imgUrl;
    }

}
