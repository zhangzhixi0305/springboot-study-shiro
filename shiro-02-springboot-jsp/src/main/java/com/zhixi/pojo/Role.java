package com.zhixi.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName Role
 * @Author zhangzhixi
 * @Description
 * @Date 2023-03-20 14:59
 * @Version 1.0
 */
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = -109410622993420652L;
    /**
     * 主键
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String name;
}
