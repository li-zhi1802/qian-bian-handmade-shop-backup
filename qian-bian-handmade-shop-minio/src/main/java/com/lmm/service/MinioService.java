package com.lmm.service;

import io.minio.errors.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author : 芝麻
 * @date : 2023-01-28 14:12
 **/
public interface MinioService {
    /**
     * 上传文件
     *
     * @param bucket
     * @param objectName
     * @param contentType
     * @param bytes
     * @throws ServerException
     * @throws InsufficientDataException
     * @throws ErrorResponseException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws InvalidResponseException
     * @throws XmlParserException
     * @throws InternalException
     */
    void uploadPlainFile(String bucket, String objectName, String contentType, byte[] bytes) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;


    /**
     * 上传本地文件
     *
     * @param bucket
     * @param objectName
     * @param contentType
     * @param fileAbsolutPath
     * @throws IOException
     * @throws ServerException
     * @throws InsufficientDataException
     * @throws ErrorResponseException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws InvalidResponseException
     * @throws XmlParserException
     * @throws InternalException
     */
    void uploadFile(String bucket, String objectName, String fileAbsolutPath) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;

    /**
     * 返回在桶中是否对象名的文件存在
     *
     * @param bucket
     * @param objectName
     * @return
     * @throws ServerException
     * @throws InsufficientDataException
     * @throws ErrorResponseException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws InvalidResponseException
     * @throws XmlParserException
     * @throws InternalException
     */
    boolean existsFile(String bucket, String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;

    /**
     * 在指定桶中下载对象名的文件
     *
     * @param bucket
     * @param objectName
     * @return
     * @throws ServerException
     * @throws InsufficientDataException
     * @throws ErrorResponseException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws InvalidResponseException
     * @throws XmlParserException
     * @throws InternalException
     */
    byte[] download(String bucket, String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;
}
