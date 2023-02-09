package com.lmm.config.properties;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author : 芝麻
 * @date : 2023-01-28 14:04
 **/
@Data
@ConfigurationProperties(prefix = "minio")
public class MinioProperties implements InitializingBean {
    public static String PICTURE_BUCKET;
    public static String VIDEO_BUCKEt;
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String pictureBucket;
    private String videoBucket;

    @Override
    public void afterPropertiesSet() throws Exception {
        PICTURE_BUCKET = pictureBucket;
        VIDEO_BUCKEt = videoBucket;
    }
}
