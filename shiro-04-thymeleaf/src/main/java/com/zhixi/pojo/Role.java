package com.zhixi.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Role
 * @Author zhangzhixi
 * @Description
 * @Date 2023-03-20 14:59
 * @Version 1.0
 */
@Data
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Role implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String name;
}
