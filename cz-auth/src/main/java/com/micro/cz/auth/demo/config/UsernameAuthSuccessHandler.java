package com.micro.cz.auth.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestValidator;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;

/**
 * @author chenzan
 * @version V1.0
 * @description TODO
 * @create-date 2018/11/21
 * @modifier
 * @modifier-data
 */
@Component
public class UsernameAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    //自己验证clintId
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String header = httpServletRequest.getHeader("Authorization");
        //根据oauth2协议进行请求头的判断
        if (header == null || !header.startsWith("Basic ")) {
            response.reset();
            response.setContentType("text/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print("{errorMgs:'auth2格式错误：请求头不包含Basic'}");
            response.getWriter().close();
        }
        //验证请求头 获取client信息
        String[] clients = extractClientHeader(header, response);
        assert clients.length == 2;
        String clientId = clients[0];
        String clentSecret = clients[1];
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        if (!clientDetails.getClientSecret().equals(clentSecret)) {
            response.reset();
            response.setContentType("text/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print("{errorMgs:'clientId not match'}");
            response.getWriter().close();
        }
        TokenRequest tokenRequest = new TokenRequest(new HashMap<>(),clientId,clientDetails.getScope(),"up");
        //校验scope
        new DefaultOAuth2RequestValidator().validateScope(tokenRequest, clientDetails);
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
        OAuth2AccessToken oAuth2AccessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);

        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.append(oAuth2AccessToken.getValue());
    }

    /**
     * 提取header中client信息
     *
     * @param header
     * @return
     */
    private String[] extractClientHeader(String header, HttpServletResponse response) throws IOException {
        //去除Basic 前缀
        byte[] base64Client;
        String clientInfo = "";
        try {
            base64Client = header.substring(6).getBytes("UTF-8");
            byte[] decode = Base64.getDecoder().decode(base64Client);
            clientInfo = new String(decode, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        int splitIndex = clientInfo.indexOf(":");
        if (splitIndex < 0) {
            response.reset();
            response.setContentType("text/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print("{errorMgs:'非法的authentication token'}");
            response.getWriter().close();
        }
        return new String[]{clientInfo.substring(0, splitIndex), clientInfo.substring(splitIndex + 1)};
    }
}
