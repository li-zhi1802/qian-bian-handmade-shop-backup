package com.lmm.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.lmm.exception.QianBianException;
import com.lmm.service.MinioService;
import com.lmm.util.MinioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class MinioServiceWrapper {
    @Autowired
    private MinioService minioService;

    public String uploadFile(MultipartFile file, String bucket) {
        byte[] bytes;
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            throw new QianBianException("获取文件字节流失败，请重试");
        }
        String fileExtension = MinioUtil.getFileExtension(file.getOriginalFilename());
        String fileMd5 = DigestUtil.md5Hex(bytes);
        String objectName = MinioUtil.todayFolder() + fileMd5 + fileExtension;
        try {
            minioService.uploadPlainFile(
                    bucket,
                    objectName,
                    file.getContentType(),
                    bytes
            );
            // 上传成功后，将文件路径返回
            // bucket + objectName
            // yyyy/MM/dd/objectName
            return bucket + "/" + objectName;
        } catch (Exception e) {
            e.printStackTrace();
            throw new QianBianException("文件上传失败");
        }
    }
}
