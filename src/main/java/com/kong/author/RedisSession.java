package com.kong.author;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * Shiro 集成 redis
 *
 * 分布式处理、
 * AbstractSessionDAO
 * 也可以继承EnterpriseCacheSessionDAO
 *      配置RedisCache 来使用
 * @author Kong, created on 2018-05-23T16:26.
 * @since 1.2.0-SNAPSHOT
 */

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class RedisSession extends EnterpriseCacheSessionDAO {

    // 设置超时时间--毫秒
    private long expireTime = 1200*1000 ;

    public static final String SHIRO_USER = "shiro_user:" ;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    private Cache<Serializable,Session> getCache(){
        return getCacheManager().getCache("shiroSession") ;
    }

    // 创建session，保存到数据库
    @Override
    public Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        String key = SHIRO_USER + session.getId().toString();
        SimpleSession simpleSession = (SimpleSession) session;
        redisTemplate.opsForValue().set(key, JSONObject.toJSONString(simpleSession), expireTime, TimeUnit.MILLISECONDS);
        getCache().put(sessionId,session) ;
        return sessionId;
    }

    // 获取session
    @Override
    public Session doReadSession(Serializable sessionId) {
        Session session = super.doReadSession(sessionId) ;
        if (null == session){
            // 从redis中获取session
            String sessionStr = redisTemplate.opsForValue().get(SHIRO_USER + sessionId.toString());
            session  =  JSONObject.parseObject(sessionStr,SimpleSession.class) ;
        }
        return session ;
    }

    // 更新session的最后一次访问时间
    @Override
    public void update(Session session) {
        super.doUpdate(session);
        session.setTimeout(expireTime);
        String key = SHIRO_USER + session.getId().toString();
        SimpleSession simpleSession = (SimpleSession) session;
        redisTemplate.opsForValue().set(key, JSONObject.toJSONString(simpleSession), expireTime, TimeUnit.MILLISECONDS);
    }

    // 删除session
    @Override
    public void delete(Session session) {
        super.doDelete(session);
        getCache().remove(session.getId()) ;
        redisTemplate.delete(SHIRO_USER + session.getId().toString());
    }

    @Override// 获取活跃的session，可以用来统计在线人数，如果要实现这个功能，可以在将session加入redis时指定一个session前缀，统计的时候则使用keys("session-prefix*")的方式来模糊查找redis中所有的session集合
    public Collection<Session> getActiveSessions() {
        return null;
    }

    /**
     *  获取超时时间
     * @return
     */
    public long getExpireTime() {
        return expireTime;
    }

}
