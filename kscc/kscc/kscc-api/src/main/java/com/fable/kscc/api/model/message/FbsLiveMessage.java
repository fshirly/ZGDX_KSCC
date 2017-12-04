package com.fable.kscc.api.model.message;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *消息实体类
 */
public class FbsLiveMessage {
  private Integer id;			//编号
  private String status;		//消息状态
  private String content;		//消息内容
  private Integer userId;		//发信人编号
  private Integer liveId;		//直播id
  private Integer addressee;	//收件人id
  private String mtype;			//消息类型
  private String creatorId;		//创建者
  private Date createdTime;		//创建时间
  private String updateId;		//修改者
  private Date updatedTime;		//修改时间
  
  
  //新增字段
  private String userName;		//发信人名字
  private String universalTime; //通用时间转义;
  private String hospitalName;   //参与方医院名字



  public void setHospitalName(String hospitalName) {
    this.hospitalName = hospitalName;
  }

  public String getHospitalName() {

    return hospitalName;
  }

  public String getUniversalTime() {
	return universalTime;
}

public void setUniversalTime(String universalTime) {
	this.universalTime = universalTime;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getLiveId() {
    return liveId;
  }

  public void setLiveId(Integer liveId) {
    this.liveId = liveId;
  }

  public Integer getAddressee() {
    return addressee;
  }

  public void setAddressee(Integer addressee) {
    this.addressee = addressee;
  }

  public String getMtype() {
    return mtype;
  }

  public void setMtype(String mtype) {
    this.mtype = mtype;
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
  
  public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", getId());
		map.put("status", getStatus());
		map.put("content", getContent());
		map.put("userid", getUserId());
		map.put("userName", getUserName());
		map.put("liveId", getLiveId());
		map.put("addressee", getAddressee());
		map.put("mtype", getMtype());
		return map;
	}
	
  
}
