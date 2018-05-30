package com.kong.model;
/**
 * Created by fanbeibei on 2017/9/18.
 */

import java.io.Serializable;
import java.util.Date;

/**
 * @author Fan Beibei
 * @Description: 当前登录用户信息
 * @date 2017/9/18 20:45
 */
public class User implements Serializable {
    private static final long serialVersionUID = 6913581685803253134L;

    /**
     * ID
     */
    private Integer id;

    /**
     * 机构ID
     */
    private Integer orgId;

    /**
     * 用户所属机构类型（1：车厂；2：4S店）
     */
    private Integer orgType;

    /**
     * 是否4S店的负责人（0：不是；1是）
     */
    private Integer isShopOrgAdmin;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password ;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别(0:未知，1：男，2：女，9：其他)
     */
    private Integer sex;

    /**
     * 身份证号
     */
    private String identityCard;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 头像路径
     */
    private String headPicture;

    /**
     * 服务到期时间
     */
    private Date serviceExpireTime;

    /**
     * 数据状态（1：可用，2：禁用，3：临时用户）
     */
    private Integer status;
    /**
     * 用户到期时间（status为3时必填）
     */
    private Date userExpireTime;

    /**
     * 登录token信息
     */
    private String token;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setUserExpireTime(Date userExpireTime) {
        this.userExpireTime = userExpireTime;
    }

    public Integer getStatus() {
        return status;
    }

    public Date getUserExpireTime() {
        return userExpireTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setServiceExpireTime(Date serviceExpireTime) {
        this.serviceExpireTime = serviceExpireTime;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public Integer getSex() {
        return sex;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public Date getServiceExpireTime() {
        return serviceExpireTime;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }

    public Integer getIsShopOrgAdmin() {
        return isShopOrgAdmin;
    }

    public void setIsShopOrgAdmin(Integer isShopOrgAdmin) {
        this.isShopOrgAdmin = isShopOrgAdmin;
    }

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", orgId=" + orgId +
                ", orgType=" + orgType +
                ", isShopOrgAdmin=" + isShopOrgAdmin +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", identityCard='" + identityCard + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", serviceExpireTime=" + serviceExpireTime +
                ", status=" + status +
                ", userExpireTime=" + userExpireTime +
                '}';
    }
}
