package com.xlibaba.ayys.entity;


public class Film {

  private String id;
  private String image;
  private String name;
  private String desc;
  private String actor;
  private long isUsed;
  private long isChecked;
  private long isPublished;
  private long cateLogId;
  private long locId;
  private long onDecade;
  private long typeId;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp expiredTime;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }


  public String getActor() {
    return actor;
  }

  public void setActor(String actor) {
    this.actor = actor;
  }


  public long getIsUsed() {
    return isUsed;
  }

  public void setIsUsed(long isUsed) {
    this.isUsed = isUsed;
  }


  public long getIsChecked() {
    return isChecked;
  }

  public void setIsChecked(long isChecked) {
    this.isChecked = isChecked;
  }


  public long getIsPublished() {
    return isPublished;
  }

  public void setIsPublished(long isPublished) {
    this.isPublished = isPublished;
  }


  public long getCateLogId() {
    return cateLogId;
  }

  public void setCateLogId(long cateLogId) {
    this.cateLogId = cateLogId;
  }


  public long getLocId() {
    return locId;
  }

  public void setLocId(long locId) {
    this.locId = locId;
  }


  public long getOnDecade() {
    return onDecade;
  }

  public void setOnDecade(long onDecade) {
    this.onDecade = onDecade;
  }


  public long getTypeId() {
    return typeId;
  }

  public void setTypeId(long typeId) {
    this.typeId = typeId;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public java.sql.Timestamp getExpiredTime() {
    return expiredTime;
  }

  public void setExpiredTime(java.sql.Timestamp expiredTime) {
    this.expiredTime = expiredTime;
  }

}
