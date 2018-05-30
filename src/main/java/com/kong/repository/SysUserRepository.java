package com.kong.repository;

import com.kong.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * note
 *
 * @author Kong, created on 2018-05-23T14:58.
 * @since 1.2.0-SNAPSHOT
 */
@Repository
public interface SysUserRepository extends JpaRepository<SysUser,Integer> {

    SysUser findByUserName(String userName) ;
}
