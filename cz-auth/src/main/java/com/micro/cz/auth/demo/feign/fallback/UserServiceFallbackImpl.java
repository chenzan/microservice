package com.micro.cz.auth.demo.feign.fallback;

import com.micro.cz.auth.demo.feign.UserService;
import com.micro.cz.common.bean.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author chenzan
 * @version V1.0
 * @description TODO
 * @create-date 2018/11/15
 * @modifier
 * @modifier-data
 */
@Slf4j
@Service
public class UserServiceFallbackImpl implements UserService {
    @Override
    public UserVO findUserByUsername(String username) {
        log.info(username + ":用户信息查询失败");
        UserVO userVO = new UserVO();
        userVO.setUserId(-1);
        return userVO;
    }
}
