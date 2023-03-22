package com.zhixi.md5;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

import java.util.Arrays;

public class TestCustomerMd5RealmAuthenicator {

    public static void main(String[] args) {

        // 1、创建安全管理器
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        // 2、注入realm
        CustomerMd5Realm realm = new CustomerMd5Realm();
        // 3、设置realm使用hash凭证匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        // 使用算法
        credentialsMatcher.setHashAlgorithmName("md5");
        // 散列次数
        credentialsMatcher.setHashIterations(1024);
        // 4、设置凭据匹配器
        realm.setCredentialsMatcher(credentialsMatcher);

        defaultSecurityManager.setRealm(realm);
        // 5、将安全管理器注入安全工具
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        // 6、通过安全工具类获取subject
        Subject subject = SecurityUtils.getSubject();

        // 7、认证
        UsernamePasswordToken token = new UsernamePasswordToken("xiaochen", "123");
        try {
            subject.login(token);
            System.out.println("登录成功");
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误");
        }

        // 判断是否已经认证
        if (subject.isAuthenticated()) {
            System.out.println("************基于角色的控制************");
            // 基于角色的权限控制，是否有admin角色
            System.out.println(subject.hasRole("admin"));
            // 基于角色的权限控制，是否有其中的一种角色信息
            System.out.println(Arrays.toString(subject.hasRoles(Arrays.asList("admin", "super"))));
            // 基于角色的权限控制，是否同时具有提供的所有权限
            System.out.println(subject.hasAllRoles(Arrays.asList("admin", "super")));
            try {
                System.out.println("************基于权限的控制************");
                // 资源标识符:操作:资源类型，比如当前用户是否对user模块具有所有权限就可以这样写（user:*:xiaochen）
                System.out.println(subject.isPermitted("user:*:xiaochen"));
                // 分别具有哪些权限
                System.out.println(Arrays.toString(subject.isPermitted("user:*:xiaochen", "user:update:xiaochen")));
                // 同时具有哪些权限
                System.out.println(subject.isPermittedAll("user:*:xiaochen", "commodity:update:xiaochen"));
            } catch (AuthorizationException e) {
                e.printStackTrace();
                System.out.println("未经授权，无法访问");
            }
        }
    }
}

