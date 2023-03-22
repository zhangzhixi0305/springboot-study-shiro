package com.zhixi.pojo;

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
public class User implements Serializable {
    private static final long serialVersionUID = -2655803400192692203L;
    private Integer id;
    private String username;
    private String password;
    private String salt;
    /**
     * 用户角色信息
     */
    private List<Role> roles;
}
