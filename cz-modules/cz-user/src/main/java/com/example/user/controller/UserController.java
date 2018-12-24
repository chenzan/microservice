package com.example.user.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.user.bean.SysUser;
import com.example.user.service.ISysUserService;
import com.micro.cz.common.base.BaseController;
import com.micro.cz.common.bean.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    ISysUserService userService;

    @ResponseBody
    @RequestMapping("/sysinfo")
    public String sysInfo() {
        return "cz-user";
    }

    @ResponseBody
    @RequestMapping("/findUserByUsername")
    public UserVO findUserByUsername(String username) {
        SysUser sysUser = userService.getOne(new QueryWrapper<SysUser>()
                .eq("username", username));
        if (sysUser == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(sysUser, userVO);
        List<String> roleList = new ArrayList<>();
        roleList.add("admin");
        roleList.add("user");
        userVO.setRoleList(roleList);
        return userVO;
    }

}
