package com.xlibaba.ayys.entity;


public class Img {

  private long id;
  private String type;
  private String source;
  private String title;
  private String tag;
  private java.sql.Timestamp updateTime;
  private long isUsed;
  private long isPublished;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }


  public java.sql.Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.sql.Timestamp updateTime) {
    this.updateTime = updateTime;
  }


  public long getIsUsed() {
    return isUsed;
  }

  public void setIsUsed(long isUsed) {
    this.isUsed = isUsed;
  }


  public long getIsPublished() {
    return isPublished;
  }

  public void setIsPublished(long isPublished) {
    this.isPublished = isPublished;
  }

}
