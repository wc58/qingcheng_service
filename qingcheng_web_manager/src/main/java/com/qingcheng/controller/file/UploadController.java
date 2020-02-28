package com.qingcheng.controller.file;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.qingcheng.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class UploadController {

    /**
     * 上传到本地服务器
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/native")
    public String nativeUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        //真实路径
        String path = request.getSession().getServletContext().getRealPath("img");
        //文件及其目录
        String fileName = new DateTime().toString("yyyy/MM/dd") + "/" /*+ UUID.randomUUID()*/ + file.getOriginalFilename();
        String filePath = path + "/" + fileName;
        //目标文件
        File desFile = new File(filePath);
        if (!desFile.getParentFile().exists()) {
            desFile.mkdirs();
        }
        try {
            file.transferTo(desFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "http://127.0.0.1:9101/img/" + fileName;
    }

    /**
     * 上传到OSS服务器
     *
     * @param file
     * @return
     */
    @PostMapping("OSS/{type}")
    public String OSSUpload(@RequestParam("file") MultipartFile file, @PathVariable(name = "type") String type) {
        //OSS连接参数
        String endpoint = ConstantPropertiesUtils.ENDPOINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        try {
            //创建实例
            OSS ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            String filename = new DateTime().toString("yyy/MM/dd") + "/" + type + "/" + UUID.randomUUID() + file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();
            ossClient.putObject(bucketName, filename, inputStream);
            return "https://" + bucketName + "." + endpoint + "/" + filename;
        } catch (IOException e) {
            e.printStackTrace();
            return "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=182992324,4078619122&fm=26&gp=0.jpg";
        }
    }

}
