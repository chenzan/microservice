package com.micro.cz.auth.demo.config;

import com.micro.cz.common.constant.UrlConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author chenzan
 * @version V1.0
 * @description TODO
 * @create-date 2018/11/15
 * @modifier
 * @modifier-data
 */
@Configuration
@EnableWebSecurity
public class CzSecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Autowired
    UsernameLoginSecurityConfigurer usernameLoginSecurityConfigurer;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry =
                http.formLogin()
                        .defaultSuccessUrl("/login/success")
                        .permitAll()
                        .and()
                        .authorizeRequests();
        registry.antMatchers(UrlConstants.OAUTH_TOKEN).permitAll();
        registry.antMatchers(UrlConstants.OAUTH_LOGIN).permitAll();
        registry.anyRequest().authenticated()
                .and()
                .csrf().disable();
        http.apply(usernameLoginSecurityConfigurer);
    }

    //    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
