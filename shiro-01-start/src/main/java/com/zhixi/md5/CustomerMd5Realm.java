package com.zhixi.md5;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Arrays;

/**
 * 使用自定义realm 加入md5 + salt +hash
 */
public class CustomerMd5Realm extends AuthorizingRealm {


    /**
     * 授权
     * @param principals subject实体
     * @return 授权对象
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 1、获取当前身份信息
        String userName = principals.getPrimaryPrincipal().toString();

        // 2、获取权限对象
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 3、添加角色（从数据库中获取）
        authorizationInfo.addRoles(Arrays.asList("admin", "user"));
        // 4、添加权限：userName这个用户在user模块具有所有权限
        authorizationInfo.addStringPermissions(Arrays.asList("user:*:" + userName));
        return authorizationInfo;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取身份信息
        String principal = token.getPrincipal().toString();

        //根据用户名查询数据库
        if ("xiaochen".equals(principal)) {
            // 参数1: 数据库用户名
            // 参数2:数据库md5+salt之后的密码
            // 参数3:注册时的随机盐，本例子中使用动态的，用户名作为随机盐
            // 参数4:realm的名字
            return new SimpleAuthenticationInfo(
                    principal,
                    "847b2f7a3705be61f6738b48bed195f8",
                    ByteSource.Util.bytes(principal),
                    this.getName());
        }
        return null;
    }
}

