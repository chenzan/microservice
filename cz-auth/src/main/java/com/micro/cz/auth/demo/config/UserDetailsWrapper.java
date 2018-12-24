package com.micro.cz.auth.demo.config;

import com.micro.cz.common.bean.UserVO;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sun.security.util.SecurityConstants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author chenzan
 * @version V1.0
 * @description TODO
 * @create-date 2018/11/15
 * @modifier
 * @modifier-data
 */
@Data
public class UserDetailsWrapper implements UserDetails {
    private Integer userId;
    private String username;
    private String password;
    private String status;
    private List<String> roleList;

    public UserDetailsWrapper(UserVO userVO) {
        this.userId = userVO.getUserId();
        this.username = userVO.getUsername();
        this.password = userVO.getPassword();
        this.status = userVO.getIsDelete();
        this.roleList = userVO.getRoleList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        for (String role : roleList) {
            authorityList.add(new SimpleGrantedAuthority(role));
        }
        authorityList.add(new SimpleGrantedAuthority("user"));
        return authorityList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
