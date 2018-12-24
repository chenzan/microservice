package com.example.user;

import com.example.user.bean.SysUser;
import com.example.user.mapper.SysUserMapper;
import com.example.user.service.ISysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author chenzan
 * @version V1.0
 * @description TODO
 * @create-date 2018/11/15
 * @modifier
 * @modifier-data
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CzUserApplication.class)
public class UserServiceTests {
    @Autowired
    ISysUserService sysUserService;

    @Test
    public void testInsert() {
        SysUser sysUser = new SysUser();
        sysUser.setCreateTime(new Date());
        sysUser.setUsername("zhangsan");
        sysUser.setPassword("123");
        sysUserService.save(sysUser);
    }
}
