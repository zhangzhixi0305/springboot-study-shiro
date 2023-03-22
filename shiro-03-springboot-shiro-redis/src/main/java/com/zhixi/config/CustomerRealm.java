package com.zhixi.config;

import com.zhixi.pojo.Perms;
import com.zhixi.pojo.User;
import com.zhixi.sevice.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    /**
     * 处理授权
     *
     * @param principals 权限数据
     * @return 返回的授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取登录用户名(从认证那里传过来的)
        User userLogin = (User) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 查询登录用户权限集合
        List<User> userList = userService.findRolesByUsernameRoles(userLogin.getUsername());
        Optional.ofNullable(userList).
                ifPresent(users -> users.forEach(user -> {
                    user.getRoles().forEach(role -> {
                                // 添加角色信息
                                simpleAuthorizationInfo.addRole(role.getName());
                                // 根据角色ID查询权限集合
                                List<Perms> permsByRoleId = userService.findPermsByRoleId(role.getId());
                                // 添加权限信息
                                Optional.ofNullable(permsByRoleId).ifPresent(perms -> perms.forEach(per -> simpleAuthorizationInfo.addStringPermission(per.getName())));
                            }
                    );
                }));
        // 添加角色权限
        return simpleAuthorizationInfo;
    }


    /**
     * 处理认证
     *
     * @param token 用户登录信息
     * @return 身份验证信息
     * @throws AuthenticationException 异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取当前登录的用户名
        String principal = token.getPrincipal().toString();
        // 查询数据库
        User user = userService.selectUserByName(principal);
        return new SimpleAuthenticationInfo(user, user.getPassword(), new MySimpleByteSource(user.getSalt()), this.getName());
    }
}