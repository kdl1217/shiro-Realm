package com.kong.service;

import com.kong.entity.SysUser;

/**
 * note
 *
 * @author Kong, created on 2018-05-23T15:15.
 * @since 1.2.0-SNAPSHOT
 */
public interface SysUserService {

    SysUser login(String userName,String password) ;
}
