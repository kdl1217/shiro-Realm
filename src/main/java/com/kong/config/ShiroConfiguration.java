package com.kong.config;

import com.kong.author.RedisSession;
import com.kong.author.RetryLimitCredentialsMatcher;
import com.kong.author.ShiroRealm;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Shiro 配置
 *
 * @author Kong, created on 2018-05-23T11:37.
 * @since 1.2.0-SNAPSHOT
 */
@Configuration
public class ShiroConfiguration {


    @Autowired
    RedisSession redisSession ;

    /**
     * 配置验证器，处理登录次数
     * @return
     */
    @Bean
    public RetryLimitCredentialsMatcher credentialsMatcher(){
        RetryLimitCredentialsMatcher credentialsMatcher = new RetryLimitCredentialsMatcher(ehCacheManager(),RedisSession.SHIRO_USER) ;
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(1);
        return credentialsMatcher ;
    }

    /**
     * 加入自己配置的验证方式到容器
     * @return
     */
    @Bean
    public ShiroRealm shiroRealm(){
        ShiroRealm shiroRealm = new ShiroRealm() ;
        shiroRealm.setCredentialsMatcher(credentialsMatcher());
        shiroRealm.setCacheManager(ehCacheManager());
        return shiroRealm ;
    }

    /**
     * EhCacheManager配置，作为二级缓存
     * @return
     */
    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager() ;
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache/ehcache-shiro.xml");
        return ehCacheManager;
    }

    @Bean
    public DefaultWebSessionManager configWebSessionManager(){
        DefaultWebSessionManager manager = new DefaultWebSessionManager();
        manager.setSessionDAO(redisSession);// 设置SessionDao
        manager.setDeleteInvalidSessions(true);// 删除过期的session
        manager.setGlobalSessionTimeout(redisSession.getExpireTime());// 设置全局session超时时间
        manager.setSessionValidationSchedulerEnabled(true);// 是否定时检查session
        manager.setCacheManager(ehCacheManager());
        return manager;
    }

    /**
     * 权限管理，配置主要是Realm的管理认证
     * @return
     */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager () ;
        securityManager.setRealm(shiroRealm());
        securityManager.setSessionManager(configWebSessionManager());
        securityManager.setCacheManager(ehCacheManager());
        return securityManager ;
    }

    /**
     * Filter工厂，设置对应的过滤条件和跳转条件
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> map = new HashMap<>();
        //登出
        map.put("/logout","anon");
        map.put("/login","anon");
//        对所有用户认证
        map.put("/**","authc");
        //登录
        shiroFilterFactoryBean.setLoginUrl("/login");
        //首页
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //错误页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/publicData");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    /**
     * 加入注解的使用，不加入这个注解不生效
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
