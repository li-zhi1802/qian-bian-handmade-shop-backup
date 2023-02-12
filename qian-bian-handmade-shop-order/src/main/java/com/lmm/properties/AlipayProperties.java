package com.lmm.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "alipay")
public class AlipayProperties {
    private String appId;
    private String appPrivateKey;
    private String alipayPublicKey;
    private String charset = "UTF=8";
    private String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
    private String format = "JSON";
    private String signType = "RSA2";
    private String notifyUrl;
}
