package com.micro.cz.auth.demo.resolver;

import org.springframework.lang.Nullable;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenzan
 * @version V1.0
 * @description TODO
 * @create-date 2018/11/30
 * @modifier
 * @modifier-data
 */
@Component
public class ExceptionResolver implements HandlerExceptionResolver {
    @Nullable
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, @Nullable Object handler,
                                         Exception ex) {
        if (response.isCommitted()) {
            return null;
        }
        try {
            if (ex instanceof InsufficientAuthenticationException) {
                response.reset();
                response.setContentType("text/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print("oauth2格式错误：" + ex.getMessage());
                response.getWriter().close();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
