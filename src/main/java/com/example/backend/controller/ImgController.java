package com.example.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/img")
public class ImgController {

    @RequestMapping(value = "/upload",headers = {"Content-Type=multipart/form-data"})
    public String addArticleImg(MultipartFile file) throws IOException {
        String filePath="/usr/local/software/projects/question_bank/front/dist/assets/pictures/upload/";
        File pathFile=new File(filePath);
        if(!pathFile.exists()){
            pathFile.mkdirs();
        }
        String originalFileName=file.getOriginalFilename();
        String newFileName= UUID.randomUUID()+originalFileName;
        File targetFile=new File(filePath,newFileName);
        file.transferTo(targetFile);
        System.out.println(targetFile.getPath());
        return targetFile.getPath();
    }
}
