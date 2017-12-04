package com.fable.kscc.api.model.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户实体类
 */
public class FbsUser {
  private Integer id;				//编号
  private String userName;			//用户名
  private String loginName;			//登录名称
  private String password;			//密码
  private String gmsfhm;			//公民身份证号码
  private String telePhone;			//电话号码
  private String mobilePhone;		//手机号码
  private String email;				//邮箱
  private Date lastLoginTime;		//最后一次登录时间
  private String lastLoginIpaddr;	//最后一次登录ip
  private String note;				//备注
  private String creatorId;			//创建者
  private Date createdTime;			//创建时间
  private String updateId;			//修改者
  private Date updateTime;			//修改时间
  private int hospitalId;			//医院id
  private String department;		//科室
  private String rank;				//职级
  private String lockStatus;		//用户是否锁定
  private Date lockTime;			//用户锁定时间
  private String roleName;          //用户角色
  private String roleCode;
  private String newVideoNum;       //(新世通帐号)编解码器
  private String sector;            //所属部门
  private String hostLevel;

  private String departname;//科室名称
  private String viewpassword;

  public String getHostLevel() {
    return hostLevel;
  }

  public void setHostLevel(String hostLevel) {
    this.hostLevel = hostLevel;
  }

  public String getViewpassword() {
    return viewpassword;
  }

  public void setViewpassword(String viewpassword) {
    this.viewpassword = viewpassword;
  }

  public String getDepartname() {
    return departname;
  }

  public void setDepartname(String departname) {
    this.departname = departname;
  }

  public void setSector(String sector) {
    this.sector = sector;
  }

  public void setNewVideoNum(String newVideoNum) {
    this.newVideoNum = newVideoNum;
  }

  public String getSector() {
    return sector;
  }

  public String getNewVideoNum() {

    return newVideoNum;
  }

  public void setRoleCode(String roleCode) {
    this.roleCode = roleCode;
  }

  public String getRoleCode() {

    return roleCode;
  }

  public String getRoleName() {
    return roleName;
  }


  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getGmsfhm() {
    return gmsfhm;
  }

  public void setGmsfhm(String gmsfhm) {
    this.gmsfhm = gmsfhm;
  }

  public String getTelePhone() {
    return telePhone;
  }

  public void setTelePhone(String telePhone) {
    this.telePhone = telePhone;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public void setMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getLastLoginTime() {
    return lastLoginTime;
  }

  public void setLastLoginTime(Date lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
  }

  public String getLastLoginIpaddr() {
    return lastLoginIpaddr;
  }

  public void setLastLoginIpaddr(String lastLoginIpaddr) {
    this.lastLoginIpaddr = lastLoginIpaddr;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public void setCreatorId(String creatorId) {
    this.creatorId = creatorId;
  }

  public Date getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }

  public String getUpdateId() {
    return updateId;
  }

  public void setUpdateId(String updateId) {
    this.updateId = updateId;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public int getHospitalId() {
    return hospitalId;
  }

  public void setHospitalId(int hospitalId) {
    this.hospitalId = hospitalId;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getRank() {
    return rank;
  }

  public void setRank(String rank) {
    this.rank = rank;
  }

  public String getLockStatus() {
    return lockStatus;
  }

  public void setLockStatus(String lockStatus) {
    this.lockStatus = lockStatus;
  }

  public Date getLockTime() {
    return lockTime;
  }

  public void setLockTime(Date lockTime) {
    this.lockTime = lockTime;
  }


    public Map<String,Object> toMap() {
    Map<String,Object> map = new HashMap<String, Object>();
    map.put("id", getId());
    map.put("userName", getUserName());
    map.put("loginName", getLoginName());
    map.put("password", getPassword());
    map.put("GMSFHM", getGmsfhm());
    map.put("telePhone", getTelePhone());
    map.put("mobilePhone", getMobilePhone());
    map.put("email", getEmail());
    map.put("lastLoginTime", getLastLoginTime());
    map.put("lastLoginIpaddr", getLastLoginIpaddr());
    map.put("note", getNote());
    map.put("creatorId", getCreatorId());
    map.put("createdTime", getCreatedTime());
    map.put("upDateId", getUpdateId());
    map.put("updateTime", getUpdateTime());
    map.put("hospitalId", getHospitalId());
    map.put("department", getDepartment());
    map.put("rank", getRank());
    map.put("lockstatus",getLockStatus());
    map.put("lockTime",getLockTime());
    return map;
  }
}
