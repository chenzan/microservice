package com.micro.cz.auth.demo.controller;

import com.micro.cz.common.base.BaseController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author chenzan
 * @version V1.0
 * @description TODO
 * @create-date 2018/11/20
 * @modifier
 * @modifier-data
 */
@Controller
public class IndexController extends BaseController {

    @RequestMapping("/login/success")
    public String indexSuccess(Map modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        modelMap.put("user", principal);
        return "success";
    }
}
