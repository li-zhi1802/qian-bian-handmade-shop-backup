package com.lmm.controller;

import com.lmm.config.properties.MinioProperties;
import com.lmm.dto.RestResult;
import com.lmm.service.impl.MinioServiceWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : 芝麻
 * @date : 2023-02-06 19:27
 **/
@Api(tags = "上传文件相关接口")
@RestController
public class MinioController {
    @Autowired
    private MinioServiceWrapper minioServiceWrapper;

    @ApiOperation("上传单个图片，文件表单项的name设置为picture")
    @PostMapping("/picture")
    public RestResult uploadPicture(MultipartFile picture) {
        return RestResult.success(minioServiceWrapper.uploadFile(picture, MinioProperties.PICTURE_BUCKET));
    }

    @ApiOperation("上传多个图片，文件表单项的name设置为pictures")
    @PostMapping("/pictures")
    public RestResult uploadPictures(MultipartFile[] pictures) {
        List<String> objectNames = new ArrayList<>(pictures.length);
        for (MultipartFile picture : pictures) {
            objectNames.add(
                    minioServiceWrapper.uploadFile(
                            picture,
                            MinioProperties.PICTURE_BUCKET
                    )
            );
        }
        return RestResult.success(objectNames);
    }

    @ApiOperation("上传单个视频，文件表单项的name设置为video")
    @PostMapping("/video")
    public RestResult uploadVideo(MultipartFile video) {
        return RestResult.success(minioServiceWrapper.uploadFile(video, MinioProperties.VIDEO_BUCKEt));
    }

    @ApiOperation("上传多个视频，文件表单项的name设置为videos")
    @PostMapping("/videos")
    public RestResult uploadVideos(MultipartFile[] videos) {
        List<String> objectNames = new ArrayList<>(videos.length);
        for (MultipartFile video : videos) {
            objectNames.add(
                    minioServiceWrapper.uploadFile(
                            video,
                            MinioProperties.VIDEO_BUCKEt
                    )
            );
        }
        return RestResult.success(objectNames);
    }
}