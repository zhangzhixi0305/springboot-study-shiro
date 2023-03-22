package com.zhixi.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @ClassName ConsumerRealm
 * @Author zhangzhixi
 * @Description
 * @Date 2023-03-12 22:17
 * @Version 1.0
 */
public class ConsumerRealm extends AuthorizingRealm {
    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 认证
     *
     * @param token 用户名密码token
     * @return 认证实例
     * @throws AuthenticationException 身份验证异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 通过token获取用户名
        String userName = token.getPrincipal().toString();
        // 这里模拟从数据库中验证用户，之后可能从JDBC、Mybatis取
        if ("xiaochen".equals(userName)) {
            // 参数1:返回数据库中正确的用户名 参数2:返回数据库中正确密码 参数3:提供当前realm的名字 this.getName();
            return new SimpleAuthenticationInfo(userName, "123", this.getName());
        }
        return null;
    }
}
