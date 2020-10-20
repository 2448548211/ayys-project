package com.xlibaba.ayys.entity;

import com.xlibaba.ayys.utils.ClassTableName;
import com.xlibaba.ayys.utils.FieldColName;

@ClassTableName(value="film")
public class Film {

  private String id;
  private String image;
  private String name;
  private String desc;
  private String actor;
  @FieldColName(value = "is_used")
  private long isUsed;
  @FieldColName(value = "is_checked")
  private long isChecked;
  @FieldColName(value = "is_published")
  private long isPublished;
  @FieldColName(value = "cate_log_id")
  private long cateLogId;
  @FieldColName(value = "loc_id")
  private long locId;
  @FieldColName(value = "on_decade")
  private long onDecade;
  @FieldColName(value = "type_id")
  private long typeId;
  @FieldColName(value = "create_time")
  private java.sql.Timestamp createTime;
  @FieldColName(value = "expired_time")
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
