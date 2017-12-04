package com.fable.kscc.api.model.hospitalInformation;

import java.util.Date;

/**
 * 医院信息实体类
 */
public class FbsHospitalInformation {
  private Integer id;
  private String hospitalContent;
  private String hospitalUrl;
  private String hospitalName;
  private Integer parentId;
  private Integer creatorId;
  private Date creatorTime;
  private Integer updateId;
  private Date updateTime;
  private String logoUrl;
  private String bucket;
  //新增参数
  private Integer pid;
  private String codec_ownership;
  private Integer codecid;
  private Integer serialNumber;
  private String newVideoNum;
  //医院地址
  private String location;

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getBucket() {
    return bucket;
  }

  public void setBucket(String bucket) {
    this.bucket = bucket;
  }

  public void setNewVideoNum(String newVideoNum) {
    this.newVideoNum = newVideoNum;
  }

  public String getNewVideoNum() {

    return newVideoNum;
  }

public Integer getSerialNumber() {
	return serialNumber;
}

public void setSerialNumber(Integer serialNumber) {
	this.serialNumber = serialNumber;
}

public Integer getPid() {
	return pid;
}

public void setPid(Integer pid) {
	this.pid = pid;
}

public Integer getCodecid() {
	return codecid;
}

public void setCodecid(Integer codecid) {
	this.codecid = codecid;
}

public String getCodec_ownership() {
	return codec_ownership;
}

public void setCodec_ownership(String codec_ownership) {
	this.codec_ownership = codec_ownership;
}

public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getHospitalContent() {
    return hospitalContent;
  }

  public void setHospitalContent(String hospitalContent) {
    this.hospitalContent = hospitalContent;
  }

  public String getHospitalUrl() {
    return hospitalUrl;
  }

  public void setHospitalUrl(String hospitalUrl) {
    this.hospitalUrl = hospitalUrl;
  }

  public String getHospitalName() {
    return hospitalName;
  }

  public void setHospitalName(String hospitalName) {
    this.hospitalName = hospitalName;
  }

  public Integer getParentId() {
    return parentId;
  }

  public void setParentId(Integer parentId) {
    this.parentId = parentId;
  }

  public Integer getCreatorId() {
    return creatorId;
  }

  public void setCreatorId(Integer creatorId) {
    this.creatorId = creatorId;
  }

  public Date getCreatorTime() {
    return creatorTime;
  }

  public void setCreatorTime(Date creatorTime) {
    this.creatorTime = creatorTime;
  }

  public Integer getUpdateId() {
    return updateId;
  }

  public void setUpdateId(Integer updateId) {
    this.updateId = updateId;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public String getLogoUrl() {
    return logoUrl;
  }

  public void setLogoUrl(String logoUrl) {
    this.logoUrl = logoUrl;
  }
}
