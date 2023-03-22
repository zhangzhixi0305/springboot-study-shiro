package com.zhixi.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName User
 * @Author zhangzhixi
 * @Description
 * @Date 2023-03-16 20:54
 * @Version 1.0
 */
@Data
//@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String salt;
    /**
     * 用户角色信息
     */
    //@JsonIgnoreProperties({"internalId", "secretKey"})
    private List<Role> roles;
}
