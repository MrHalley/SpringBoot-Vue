package org.sang.controller;

import org.sang.model.UploadMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *  单文件上传
 */
@RestController
public class FileUploadController {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
    @PostMapping("/upload")
    public String upload(MultipartFile uploadFile, HttpServletRequest request){
        if(uploadFile.isEmpty()){
            return "未选择文件！";
        }
        String realPath = request.getServletContext().getRealPath("/uploadFile/");
        String format = sdf.format(new Date());
        File folder = new File(realPath+format);
        if(!folder.isDirectory()){
            folder.mkdirs();
        }
        String oldName = uploadFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString() +
                oldName.substring(oldName.lastIndexOf("."),oldName.length());
        try{
            uploadFile.transferTo(new File(folder,newName));
            String filePath = request.getScheme() + "://" + request.getServerName() +
                    ":" +request.getServerPort() + "/uploadFile/" + format + newName;
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传文件失败";
    }

    @PostMapping("/uploads")
    public UploadMessage uploads(MultipartFile[] uploadFiles, HttpServletRequest req){
        String realPath = req.getServletContext().getRealPath("/uploadFile/");
        String format = sdf.format(new Date());
        File folder = new File(realPath + format);
        if(!folder.isDirectory()){
            folder.mkdirs();
        }
        UploadMessage uploadMessage = new UploadMessage();
        for(MultipartFile file : uploadFiles){
            if(file.isEmpty()){

                continue;
            }
            String oldName = file.getOriginalFilename();
            String newName = UUID.randomUUID() + oldName.substring(oldName.lastIndexOf("."),oldName.length());
            try{
                file.transferTo(new File(folder,newName));
                String filePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/uploadFile/" + format + newName;
                uploadMessage.getFiles().put(newName,filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        uploadMessage.setStatus(1);
        uploadMessage.setMessage("上传成功");
        return uploadMessage;
    }
}
