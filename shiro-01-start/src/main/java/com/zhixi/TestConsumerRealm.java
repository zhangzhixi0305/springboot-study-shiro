package com.zhixi;

import com.zhixi.realm.ConsumerRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * @ClassName TestConsumerRealm
 * @Author zhangzhixi
 * @Description
 * @Date 2023-03-12 22:19
 * @Version 1.0
 */
public class TestConsumerRealm {
    public static void main(String[] args) {

        //创建securityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        //设置自定义realm
        defaultSecurityManager.setRealm(new ConsumerRealm());
        //将安全工具类设置安全工具类
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //通过安全工具类获取subject
        Subject subject = SecurityUtils.getSubject();
        //创建token
        UsernamePasswordToken token = new UsernamePasswordToken("xiaochen", "123");
        //执行登录
        try {
            subject.login(token);
        } catch (UnknownAccountException unknownAccountException) {
            unknownAccountException.printStackTrace();
            System.out.println("用户名验证错误");
        } catch (IncorrectCredentialsException incorrectCredentialsException) {
            incorrectCredentialsException.printStackTrace();
            System.out.println("密码验证错误");
        }

    }
}
