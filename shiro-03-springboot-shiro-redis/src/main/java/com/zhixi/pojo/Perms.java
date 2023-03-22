package com.zhixi.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Perms implements Serializable {
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
