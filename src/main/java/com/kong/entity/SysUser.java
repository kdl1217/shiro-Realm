package com.kong.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 *
 * @author Aaric, created on 2017-09-18T16:11.
 * @since 1.0-SNAPSHOT
 */
@Entity
@Table(name = "t_sys_user")
public class SysUser implements Serializable {

    /**
     *ID
     */
    private Integer id;

    /**
     *所属机构(用户编制所在机构)
     */
    private Integer orgId;

    /**
     * 是否机构管理员（0：不是；1：是）
     */
    private Integer isOrgAdmin;

    /**
     *用户名
     */
    private String userName;

    /**
     * 头像路径
     */
    private String headPicture;

    /**
     *密码
     */
    private String password;

    /**
     *姓名
     */
    private String name;

    /**
     *性别(0:未知，1：男，2：女，9：其他)
     */
    private Integer sex;

    /**
     *身份证号
     */
    private String identityCard;

    /**
     *电子邮箱
     */
    private String email;

    /**
     *手机号
     */
    private String mobile;

    /**
     *服务到期时间
     */
    private Date serviceExpireTime;

    /**
     *数据状态（1：可用，2：禁用，3：临时用户）
     */
    private Integer status;

    /**
     *用户到期时间（status为3时必填）
     */
    private Date userExpireTime;

    /**
     *QQ号
     */
    private String qq;

    /**
     *联系地址
     */
    private String address;

    /**
     *备注
     */
    private String remark;

    /**
     *是否已删除（1:是，0:否）
     */
    private Integer isDel;

    /**
     *数据创建时间
     */
    private Date createTime;

    /**
     *数据创建人ID
     */
    private Integer createId;

    /**
     *数据更新时间
     */
    private Date updateTime;

    /**
     *数据更新人ID
     */
    private Integer updateId;

    /**
     * 设置 id
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取 ID
     * @return id
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    /**
     * 设置 orgId
     * @param orgId 所属机构(用户编制所在机构)
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    /**
     * 获取 所属机构(用户编制所在机构)
     * @return orgId
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * 设置 userName
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取 用户名
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置 password
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取 密码
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置 name
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 姓名
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 sex
     * @param sex 性别(0:未知，1：男，2：女，9：其他)
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取 性别(0:未知，1：男，2：女，9：其他)
     * @return sex
     */
    @Column(columnDefinition = "tinyint(4)")
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置 identityCard
     * @param identityCard 身份证号
     */
    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    /**
     * 获取 身份证号
     * @return identityCard
     */
    public String getIdentityCard() {
        return identityCard;
    }

    /**
     * 设置 email
     * @param email 电子邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取 电子邮箱
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置 mobile
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取 手机号
     * @return mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置 serviceExpireTime
     * @param serviceExpireTime 服务到期时间
     */
    public void setServiceExpireTime(Date serviceExpireTime) {
        this.serviceExpireTime = serviceExpireTime;
    }

    /**
     * 获取 服务到期时间
     * @return serviceExpireTime
     */
    public Date getServiceExpireTime() {
        return serviceExpireTime;
    }

    /**
     * 设置 status
     * @param status 数据状态（1：可用，2：禁用，3：临时用户）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取 数据状态（1：可用，2：禁用，3：临时用户）
     * @return status
     */
    @Column(columnDefinition = "tinyint(4)")
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置 userExpireTime
     * @param userExpireTime 用户到期时间（status为3时必填）
     */
    public void setUserExpireTime(Date userExpireTime) {
        this.userExpireTime = userExpireTime;
    }

    /**
     * 获取 用户到期时间（status为3时必填）
     * @return userExpireTime
     */
    public Date getUserExpireTime() {
        return userExpireTime;
    }

    /**
     * 设置 qq
     * @param qq QQ号
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * 获取 QQ号
     * @return qq
     */
    public String getQq() {
        return qq;
    }

    /**
     * 设置 address
     * @param address 联系地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取 联系地址
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置 remark
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取 备注
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 isDel
     * @param isDel 是否已删除（1:是，0:否）
     */
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    /**
     * 获取 是否已删除（1:是，0:否）
     * @return isDel
     */
    @Column(columnDefinition = "tinyint(4)")
    public Integer getIsDel() {
        return isDel;
    }

    /**
     * 设置 createTime
     * @param createTime 数据创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取 数据创建时间
     * @return createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置 createId
     * @param createId 数据创建人ID
     */
    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    /**
     * 获取 数据创建人ID
     * @return createId
     */
    public Integer getCreateId() {
        return createId;
    }

    /**
     * 设置 updateTime
     * @param updateTime 数据更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取 数据更新时间
     * @return updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置 updateId
     * @param updateId 数据更新人ID
     */
    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    /**
     * 获取 数据更新人ID
     * @return updateId
     */
    public Integer getUpdateId() {
        return updateId;
    }

    @Column(columnDefinition = "tinyint(4)")
    public Integer getIsOrgAdmin() {
        return isOrgAdmin;
    }

    public void setIsOrgAdmin(Integer isOrgAdmin) {
        this.isOrgAdmin = isOrgAdmin;
    }

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }
}
