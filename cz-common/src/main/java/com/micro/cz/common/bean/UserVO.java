package com.micro.cz.common.bean;

import lombok.Data;

import java.util.Date;
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
public class UserVO {

    /**
     * 主键id
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 0-正常，1-删除
     */
    private String isDelete;

    private List<String> roleList;

}
