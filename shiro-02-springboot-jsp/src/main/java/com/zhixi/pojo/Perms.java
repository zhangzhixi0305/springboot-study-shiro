package com.zhixi.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Perms
 * @Author zhangzhixi
 * @Description
 * @Date 2023-03-20 15:00
 * @Version 1.0
 */
@Data
public class Perms implements Serializable {
    private static final long serialVersionUID = 3927224502077072585L;
    private Integer id;
    /**
     * 权限字符串
     */
    private String name;

    /**
     * 权限url
     */
    private String url;

}
