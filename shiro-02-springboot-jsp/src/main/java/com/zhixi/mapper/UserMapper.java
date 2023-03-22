package com.zhixi.mapper;

import com.zhixi.pojo.Perms;
import com.zhixi.pojo.Role;
import com.zhixi.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName UserDao
 * @Author zhangzhixi
 * @Description
 * @Date 2023-03-16 20:57
 * @Version 1.0
 */
@Repository
public interface UserMapper {
    /**
     * 保存用户
     * @param user 实体
     * @return =1表示添加成功
     */
    int saveUser(User user);

    /**
     * 根据用户名查询用户
     * @param userName 用户名
     * @return 用户实体
     */
    User selectUserByName(String userName);

    /**
     * 根据用户名查询角色信息
     * @param username 用户名
     * @return 用户角色集合
     */
    List<User> findRolesByUsernameRoles(String username);

    /**
     * 根据角色ID查询权限集合
     * @param id 角色ID
     * @return 角色的权限集合
     */
    List<Perms> findPermsByRoleId(Integer id);
}
