package com.example.user.controller;

import com.micro.cz.common.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chenzan
 * @version V1.0
 * @description TODO
 * @create-date 2018/11/13
 * @modifier
 * @modifier-data
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @ResponseBody
    @RequestMapping("/sysinfo")
    public String sysInfo() {
        return "cz-user";
    }
}
