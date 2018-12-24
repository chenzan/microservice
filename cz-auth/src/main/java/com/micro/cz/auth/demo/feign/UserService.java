package com.micro.cz.auth.demo.feign;

import com.micro.cz.auth.demo.feign.fallback.UserServiceFallbackImpl;
import com.micro.cz.common.bean.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author chenzan
 * @version V1.0
 * @description TODO
 * @create-date 2018/11/15
 * @modifier
 * @modifier-data
 */
@FeignClient(name = "cz-user", fallback = UserServiceFallbackImpl.class)
public interface UserService {
    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    @GetMapping("/user/findUserByUsername")
    UserVO findUserByUsername(@RequestParam("username") String username);

}
