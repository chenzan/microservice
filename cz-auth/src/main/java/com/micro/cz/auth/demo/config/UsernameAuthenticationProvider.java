package com.micro.cz.auth.demo.config;

import com.micro.cz.auth.demo.feign.UserService;
import com.micro.cz.common.bean.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author chenzan
 * @version V1.0
 * @description TODO
 * @create-date 2018/11/20
 * @modifier
 * @modifier-data
 */
public class UsernameAuthenticationProvider implements AuthenticationProvider {
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernameAuthenticationToken usernameAuthenticationToken = (UsernameAuthenticationToken) authentication;
        UserVO userVo = userService.findUserByUsername((String) usernameAuthenticationToken.getPrincipal());
        if (userVo == null) {
            throw new UsernameNotFoundException("账号不存在：" + usernameAuthenticationToken.getPrincipal());
        }
        UserDetailsWrapper userDetailsWrapper = new UserDetailsWrapper(userVo);
        UsernameAuthenticationToken authenticationToken = new UsernameAuthenticationToken(userDetailsWrapper,
                userDetailsWrapper.getAuthorities());
        authenticationToken.setDetails(usernameAuthenticationToken.getDetails());
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernameAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
