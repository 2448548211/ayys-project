package com.xlibaba.ayys.entity;


public class Information {

  private long id;
  private String title;
  private String type;
  private String source;
  private java.sql.Timestamp updateTime;
  private String viewCount;
  private long isUsed;
  private long isPublished;
  private long isChecked;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
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


  public java.sql.Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.sql.Timestamp updateTime) {
    this.updateTime = updateTime;
  }


  public String getViewCount() {
    return viewCount;
  }

  public void setViewCount(String viewCount) {
    this.viewCount = viewCount;
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


  public long getIsChecked() {
    return isChecked;
  }

  public void setIsChecked(long isChecked) {
    this.isChecked = isChecked;
  }

}
