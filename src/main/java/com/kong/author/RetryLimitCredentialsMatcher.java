package com.kong.author;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *  验证器，增加了登录次数校验功能
 *      ShiroRealm继承AuthorizingRealm，
 *      在其父类AuthenticatingRealm的getAuthenticationInfo方法中会调用credentialsMatcher的 doCredentialsMatch
 *          来验证用户输入用户名密码是否匹配。
 * @author Kong, created on 2018-05-28T13:59.
 * @since 1.2.0-SNAPSHOT
 */
public class RetryLimitCredentialsMatcher extends HashedCredentialsMatcher {

    //集群中可能会导致出现验证多过5次的现象，因为AtomicInteger只能保证单节点并发
    private Cache<String, AtomicInteger> lgoinRetryCache;

    //最大重试次数--默认5次
    private int maxRetryCount = 5;

    private String lgoinRetryCacheName;

    public void setMaxRetryCount(int maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }

    public RetryLimitCredentialsMatcher(CacheManager cacheManager,String lgoinRetryCacheName) {
        this.lgoinRetryCacheName = lgoinRetryCacheName;
        lgoinRetryCache = cacheManager.getCache(lgoinRetryCacheName);
    }

    /**
     * 信息比较
     *      登录验证策略
     * @param token
     * @param info
     * @return
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        //retry count + 1
        AtomicInteger retryCount = lgoinRetryCache.get(username);
        if (null == retryCount) {
            retryCount = new AtomicInteger(0);
            lgoinRetryCache.put(username, retryCount);
        }
        if (retryCount.incrementAndGet() > maxRetryCount) {
            throw new ExcessiveAttemptsException();
        }else{
            lgoinRetryCache.put(username, retryCount);
        }

        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            //clear retry data
            lgoinRetryCache.remove(username);
        }
        return matches;
    }
}
