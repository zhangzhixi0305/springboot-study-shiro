package com.zhixi.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.zhixi.cache.RedisCacheManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ShiroConfig
 * @Author zhangzhixi
 * @Description
 * @Date 2023-03-15 16:35
 * @Version 1.0
 */
@Configuration
public class ShiroConfig {

    /**
     * 主要作用让Shiro整合Thymeleaf的标签生效
     * @return 方言
     */
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }


    /**
     * shiro过滤器工厂
     * @param securityManager shiro安全管理器
     * @return 过滤器
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager) {
        // 1、创建shiro的filter
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 2、注入安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, String> authMap = new HashMap<>();
        // 配置公共资源
        authMap.put("/login.jsp", "anon");
        authMap.put("/user/*", "anon");
        // 配置受限资源
        authMap.put("/**", "authc");
        // 默认认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/user/loginview");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(authMap);
        return shiroFilterFactoryBean;
    }

    /**
     * shiro的安全管理器
     * @param realm realm
     * @return shiro的安全管理器
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager getSecurityManager(@Qualifier("realm") Realm realm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    /**
     * 创建自定义realm
     * @return 自定义Realm
     */
    @Bean("realm")
    public Realm getRealm(){
        CustomerRealm customerRealm = new CustomerRealm();
        //设置hashed凭证匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置md5加密
        credentialsMatcher.setHashAlgorithmName("md5");
        //设置散列次数
        credentialsMatcher.setHashIterations(1024);
        customerRealm.setCredentialsMatcher(credentialsMatcher);

        //开启缓存管理
        customerRealm.setCacheManager(new RedisCacheManager());
        customerRealm.setCachingEnabled(true);//开启全局缓存
        customerRealm.setAuthenticationCachingEnabled(true);//认证认证缓存
        customerRealm.setAuthenticationCacheName("authenticationCache");
        customerRealm.setAuthorizationCachingEnabled(true);//开启授权缓存
        customerRealm.setAuthorizationCacheName("authorizationCache");
        return customerRealm;
    }
}
