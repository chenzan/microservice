package com.micro.cz.auth.demo.config;

import com.micro.cz.auth.demo.feign.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author chenzan
 * @version V1.0
 * @description TODO
 * @create-date 2018/11/20
 * @modifier
 * @modifier-data
 */
@Component
public class UsernameLoginSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    UserService userService;
    @Autowired
    UsernameAuthFailHandler usernameAuthFailHandler;
    @Autowired
    UsernameAuthSuccessHandler usernameAuthSuccessHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        UsernameLoginAuthFilter usernameLoginAuthFilter = new UsernameLoginAuthFilter();
        usernameLoginAuthFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        usernameLoginAuthFilter.setAuthenticationSuccessHandler(usernameAuthSuccessHandler);
        usernameLoginAuthFilter.setAuthenticationFailureHandler(usernameAuthFailHandler);

        UsernameAuthenticationProvider usernameAuthenticationProvider = new UsernameAuthenticationProvider();
        usernameAuthenticationProvider.setUserService(userService);

        http.authenticationProvider(usernameAuthenticationProvider)
                .addFilterAfter(usernameLoginAuthFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
