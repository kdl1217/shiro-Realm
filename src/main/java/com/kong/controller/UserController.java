package com.kong.controller;

import com.alibaba.fastjson.JSON;
import com.kong.entity.SysUser;
import com.kong.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Kong on 2018/3/23.
 */
@RestController
public class UserController {

    @Autowired
    private SysUserService sysUserService ;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam String username,@RequestParam String password) throws Exception {
        SysUser sysUser = sysUserService.login(username,password) ;
        return JSON.toJSONString(sysUser);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() throws Exception {
        Subject subject = SecurityUtils.getSubject() ;
        if (subject.isAuthenticated()){
            //使用shiro来验证
            subject.logout();
        }
        return "1" ;
    }

    @RequiresPermissions("/privateData")
    @RequestMapping(value = "/privateData", method = RequestMethod.GET)
    public String privteData() throws Exception {
        Subject subject = SecurityUtils.getSubject() ;
        return JSON.toJSONString(subject.getPrincipal()) ;
    }


    @RequiresPermissions("permission")
    @RequestMapping(value = "/permission", method = RequestMethod.GET)
    public String permission() throws Exception {
        Subject subject = SecurityUtils.getSubject() ;
        return JSON.toJSONString(subject.getPrincipal()) ;
    }

    @RequestMapping(value = "/publicData", method = RequestMethod.GET)
    public String publicData() throws Exception {

        return "1" ;
    }

}
