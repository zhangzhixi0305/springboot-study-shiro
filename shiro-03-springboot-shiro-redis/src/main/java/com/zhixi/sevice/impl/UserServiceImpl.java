package com.zhixi.sevice.impl;

import com.zhixi.mapper.UserMapper;
import com.zhixi.pojo.Perms;
import com.zhixi.pojo.User;
import com.zhixi.sevice.UserService;
import com.zhixi.util.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Author zhangzhixi
 * @Description
 * @Date 2023-03-16 20:59
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int saveUser(User user) {
        //处理业务调用dao
        //1.生成随机盐
        String salt = SaltUtils.getSalt(8);
        //2.将随机盐保存到数据
        user.setSalt(salt);
        //3.明文密码进行md5 + salt + hash散列
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
        user.setPassword(md5Hash.toHex());
        return userMapper.saveUser(user);
    }

    @Override
    public User selectUserByName(String userName) {
        return userMapper.selectUserByName(userName);
    }

    @Override
    public  List<User>  findRolesByUsernameRoles(String username) {
        return userMapper.findRolesByUsernameRoles(username);
    }

    @Override
    public List<Perms> findPermsByRoleId(Integer id) {
        return userMapper.findPermsByRoleId(id);
    }
}
