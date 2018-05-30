package com.kong.service.impl;

import com.kong.entity.SysUser;
import com.kong.repository.SysUserRepository;
import com.kong.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * note
 *
 * @author Kong, created on 2018-05-23T15:16.
 * @since 1.2.0-SNAPSHOT
 */
@Service
public class SysUserServiceImpl implements SysUserService {


    @Autowired
    private SysUserRepository sysUserRepository ;

    @Override
    public SysUser login(String userName, String password) {

        UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
        //获取当前subject
        Subject subject = SecurityUtils.getSubject();
//        if (subject.isAuthenticated()){
//            throw new UnauthorizedException() ;
//        }
        //登录
        subject.login(token);
        return (SysUser) subject.getPrincipal();
    }
}
