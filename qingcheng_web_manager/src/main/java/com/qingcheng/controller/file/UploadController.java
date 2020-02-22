package com.qingcheng.controller.file;

import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @RequestMapping("/native")
    public String nativeUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        //真实路径
        String path = request.getSession().getServletContext().getRealPath("img");
        //文件及其目录
        String fileName = new DateTime().toString("yyyy/MM/dd") + "/" + UUID.randomUUID() + file.getOriginalFilename();
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

}
