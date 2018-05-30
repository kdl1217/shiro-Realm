package com.kong.author;

import com.kong.entity.SysUser;
import com.kong.repository.SysUserRepository;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Shiro登录验证、授权
 *
 * @author Kong, created on 2018-05-23T11:16.
 * @since 1.2.0-SNAPSHOT
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    SysUserRepository userRepository ;

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        //给当前用户授权的权限（功能权限、角色）
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo() ;

        SysUser user = (SysUser) principals.getPrimaryPrincipal();

        authorizationInfo.addRole(user.getName());
        authorizationInfo.addStringPermission("/privateData");

        return authorizationInfo;
    }

    /**
     * 登录验证
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
//        if (authcToken.getPrincipal() == null) {
//            return null;
//        }

        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        //获取用户信息
        String name = token.getUsername();
        String passWord = String.valueOf(token.getPassword()) ;
        SysUser user = userRepository.findByUserName(name);
        if (user == null) {
            //TODO 异常处理--> 用户不存在
            //这里返回后会报出对应异常
            return null;
        }else {
//            63A9F0EA7BB98050796B649E85481845
            //加[盐] 一般以用户名或随机数为盐
            ByteSource salt = ByteSource.Util.bytes("");

            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(),salt, getName());
            return simpleAuthenticationInfo;
        }
    }

}
