package com.micro.cz.auth.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author chenzan
 * @version V1.0
 * @description TODO
 * @create-date 2018/12/21
 * @modifier
 * @modifier-data
 */
@Configuration
@EnableAuthorizationServer
public class CzAuthorizationConfig extends AuthorizationServerConfigurerAdapter {
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("aaa")
                .secret("123456")
                .authorizedGrantTypes("up")
                .scopes("auth");
    }
}
