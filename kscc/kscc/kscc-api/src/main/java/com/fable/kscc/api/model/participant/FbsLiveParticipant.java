package com.fable.kscc.api.model.participant;

import java.util.Date;

public class FbsLiveParticipant {
  private Integer id;				//主键
  private Integer hospitalId;		//医院ID
  private String roleId;			//播放角色ID
  private Integer serialnumber;		//主持人序列号
  private Integer liveId;			//所属直播
  private String participation;		//是否参与 1，参与 2，拒绝
  private String onlineStatus;		//用户是否在线状态
  private String newvideoNum;		//新视通账号
  private String videoStatus;		//录像状态
  private String mtId;				//终端号
  private String creatorId;			//创建者
  private Date createdTime;			//创建时间
  private String updateId;			//修改者
  private Date updatedTime;			//修改时间
  private String ishost;			//是否为主持人
  //增加参与方医院名称
  private String hospitalName;
  
  public String getHospitalName() {
	return hospitalName;
}

public void setHospitalName(String hospitalName) {
	this.hospitalName = hospitalName;
}

public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getHospitalId() {
    return hospitalId;
  }

  public void setHospitalId(Integer hospitalId) {
    this.hospitalId = hospitalId;
  }

  public String getRoleId() {
    return roleId;
  }

  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }

  public Integer getSerialnumber() {
    return serialnumber;
  }

  public void setSerialnumber(Integer serialnumber) {
    this.serialnumber = serialnumber;
  }

  public Integer getLiveId() {
    return liveId;
  }

  public void setLiveId(Integer liveId) {
    this.liveId = liveId;
  }

  public String getParticipation() {
    return participation;
  }

  public void setParticipation(String participation) {
    this.participation = participation;
  }

  public String getOnlineStatus() {
    return onlineStatus;
  }

  public void setOnlineStatus(String onlineStatus) {
    this.onlineStatus = onlineStatus;
  }

  public String getNewvideoNum() {
    return newvideoNum;
  }

  public void setNewvideoNum(String newvideoNum) {
    this.newvideoNum = newvideoNum;
  }

  public String getVideoStatus() {
    return videoStatus;
  }

  public void setVideoStatus(String videoStatus) {
    this.videoStatus = videoStatus;
  }

  public String getMtId() {
    return mtId;
  }

  public void setMtId(String mtId) {
    this.mtId = mtId;
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

  public Date getUpdatedTime() {
    return updatedTime;
  }

  public void setUpdatedTime(Date updatedTime) {
    this.updatedTime = updatedTime;
  }

  public String getIshost() {
    return ishost;
  }

  public void setIshost(String ishost) {
    this.ishost = ishost;
  }
}
