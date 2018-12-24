package com.micro.cz.auth.demo.config;

import com.micro.cz.common.constant.UrlConstants;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chenzan
 * @version V1.0
 * @description TODO
 * @create-date 2018/11/20
 * @modifier
 * @modifier-data
 */

public class UsernameLoginAuthFilter extends AbstractAuthenticationProcessingFilter {


    protected UsernameLoginAuthFilter() {
        super(new AntPathRequestMatcher(UrlConstants.OAUTH_LOGIN));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        String username = httpServletRequest.getParameter("username");
        if (username == null) {
            username = "";
        }
        username = username.trim();
        UsernameAuthenticationToken usernameAuthenticationToken = new UsernameAuthenticationToken(username);
        setDetails(httpServletRequest, usernameAuthenticationToken);
        return this.getAuthenticationManager().authenticate(usernameAuthenticationToken);
    }

    protected void setDetails(HttpServletRequest request, UsernameAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

}
