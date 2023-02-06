package com.lmm.service.impl;

import com.lmm.service.MinioService;
import io.minio.*;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author : 芝麻
 * @date : 2023-01-28 14:12
 **/
@Slf4j
@Service
public class MinioServiceImpl implements MinioService {
    @Autowired
    private MinioClient minioClient;

    @Override
    public void uploadPlainFile(String bucket, String objectName, String contentType, byte[] bytes) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        try (ByteArrayInputStream bai = new ByteArrayInputStream(bytes)) {
            minioClient.putObject(
                    PutObjectArgs
                            .builder()
                            .bucket(bucket)
                            //yyyy/MM/dd/fileMd5 fileExtension
                            .object(objectName)
                            .stream(bai, bai.available(), -1)
                            .contentType(contentType)
                            .build()
            );
        }
    }

    @Override
    public void uploadFile(String bucket, String objectName, String fileAbsolutPath) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient.uploadObject(
                UploadObjectArgs
                        .builder()
                        .bucket(bucket)
                        .object(objectName)
                        .filename(fileAbsolutPath)
                        .build());
    }

    @Override
    public boolean existsFile(String bucket, String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        boolean bucketExists = minioClient.bucketExists(
                BucketExistsArgs
                        .builder()
                        .bucket(bucket)
                        .build());
        if (!bucketExists) {
            return false;
        }
        // 如果查得到就说明存在
        GetObjectResponse response = minioClient.getObject(
                GetObjectArgs
                        .builder()
                        .bucket(bucket)
                        .object(objectName)
                        .build()
        );
        return response != null && response.readAllBytes().length != 0;
    }

    @Override
    public byte[] download(String bucket, String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioClient.getObject(
                GetObjectArgs
                        .builder()
                        .bucket(bucket)
                        .object(objectName)
                        .build()
        ).readAllBytes();
    }
}
