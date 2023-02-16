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
    private String charset;
    private String gatewayUrl;
    private String format;
    private String signType;
    private String notifyUrl;
    private String returnUrl;
}
