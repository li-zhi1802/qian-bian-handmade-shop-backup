package com.lmm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import javax.annotation.Resource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Resource(name = "authorizationServerTokenServicesCustom")
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("qbWebApp")
                .secret(bCryptPasswordEncoder.encode("qbWebApp"))
                .resourceIds("qian-bian")
                .authorizedGrantTypes(
                        "authorization_code",
                        "password",
                        "client_credentials",
                        "implicit",
                        "refresh_token"
                )
                .scopes("all")
                .autoApprove(false)
                .redirectUris("localhost:8080")
        ;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                //???????????????
                .authenticationManager(authenticationManager)
                //??????????????????
                .tokenServices(authorizationServerTokenServices)
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

    //???????????????????????????
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                //oauth/token_key?????????
                .tokenKeyAccess("permitAll()")
                //oauth/check_token??????
                .checkTokenAccess("permitAll()")
                //??????????????????????????????
                .allowFormAuthenticationForClients()
        ;
    }

}
