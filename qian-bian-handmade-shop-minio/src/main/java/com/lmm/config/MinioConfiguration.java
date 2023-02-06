package com.lmm.config;

import com.lmm.config.properties.MinioProperties;
import io.minio.MinioClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : 芝麻
 * @date : 2023-01-28 14:07
 **/
@Configuration
@EnableConfigurationProperties(MinioProperties.class)
public class MinioConfiguration {
    @Bean
    public MinioClient minioClient(MinioProperties minioProperties) {
        return MinioClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }
}
